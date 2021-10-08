package ru.julia.salarySpringLiquibase2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.julia.salarySpringLiquibase2.dto.MapValueComparator;
import ru.julia.salarySpringLiquibase2.dto.SalaryAndKPI;
import ru.julia.salarySpringLiquibase2.dto.TotalComparator;
import ru.julia.salarySpringLiquibase2.entities.Kpi;
import ru.julia.salarySpringLiquibase2.entities.Salary;
import ru.julia.salarySpringLiquibase2.entities.Total;
import ru.julia.salarySpringLiquibase2.repositories.DepartmentRepository;
import ru.julia.salarySpringLiquibase2.repositories.KpiRepository;
import ru.julia.salarySpringLiquibase2.repositories.SalaryRepository;
import ru.julia.salarySpringLiquibase2.repositories.TotalRepository;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TotalServiceImpl implements TotalService {
    private final TotalRepository totalRepository;
    private final SalaryRepository salaryRepository;
    private final KpiRepository kpiRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public List<Total> findAllTotals() {
        return totalRepository.findAll();
    }

    @Override
    public void makeTotalCsvAndFillTable(String fileName) throws FileNotFoundException {
        List<Salary> salaries = salaryRepository.findAll();
        List<Kpi> kpis = kpiRepository.findAll();
        List<Total> totals = new ArrayList<>();
        Map<Integer, SalaryAndKPI> map = new HashMap<>();
        for (int i = 0; i < salaries.size(); i++) {
            Integer key = salaries.get(i).getSalaryId();
            Salary currentSalary = salaries.get(i);
            SalaryAndKPI salaryAndKPI = new SalaryAndKPI(currentSalary, null);
            map.put(key, salaryAndKPI);
        }

        for (int i = 0; i < kpis.size(); i++) {
            Integer key = kpis.get(i).getKpiId();
            Kpi kpi = kpis.get(i);
            Salary salary = map.get(key).getSalary();
            SalaryAndKPI salaryAndKPI = new SalaryAndKPI(salary, kpi);
            map.put(key, salaryAndKPI);
        }

        PrintStream csv = new PrintStream(fileName);
        csv.println(
                "total_id" + "name" + ";" + "salary" + ";" + "kpi" + ";" + "total"
        );
        for (SalaryAndKPI value : map.values()) {
            Integer sum = value.getSalary().getSalary() + value.getKpi().getKpi();
            Total total = new Total(
                    value.getSalary().getSalaryId(),
                    value.getSalary().getName(),
                    value.getSalary().getSalary(),
                    value.getKpi().getKpi(),
                    sum);
            totals.add(total);

            csv.println(
                    total.getTotalId() + ";" +
                            total.getName() + ";" +
                            total.getSalary() + ";" +
                            total.getKpi() + ";" +
                            total.getTotal()
            );
        }
        totalRepository.saveAll(totals);
    }

    @Override
    public List<Total> sortTotalAsc() {
        List<Total> totals = totalRepository.findAll();
        Comparator totalComparator = new TotalComparator();
        Collections.sort(totals, totalComparator);
        return totals;
    }

    @Override
    public Integer getTotalSum() {
        List<Total> totals = totalRepository.findAll();
        Integer sum = 0;
        for (Total total : totals) {
            sum = sum + total.getTotal();
        }
        return sum;
    }

    @Override
    public List<Map.Entry<String, Integer>> getDepartmentCostsAcs() {
        List<Total> totals = totalRepository.findAll();
        Map<String, Integer> map = new TreeMap<>(); // ??? отсортирует по ключу
        for (Total total : totals) {
            Integer departmentId = salaryRepository.findById(total.getTotalId()).orElseThrow().getDepartmentId();
            String key = departmentRepository.findById(departmentId).orElseThrow().getDepartment();
            Integer value = total.getTotal();
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + value);
            } else {
                map.put(key, value);
            }
        }
        List<Map.Entry<String, Integer>> list = new ArrayList(map.entrySet()); // ??? это чтобы отсортировать
        // по значению, разумно ли так делать?
        Comparator<Map.Entry<String, Integer>> mapValueComparator = new MapValueComparator();
        list.sort(mapValueComparator);
        return list;
    }

    @Override
    public Map<String, Integer> getDepartmentWithMaxCosts() {
        List<Map.Entry<String, Integer>> list = getDepartmentCostsAcs();
        Map<String, Integer> mapMax = new TreeMap<>();
        Integer max = 0;
        String keyMax = "";
        for (Map.Entry<String, Integer> entry : list) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                keyMax = entry.getKey();
            }
        }
        mapMax.put(keyMax, max);
        return mapMax;
    }

    @Override
    public Map<String, Integer> getDepartmentWithMinCosts() {
        List<Map.Entry<String, Integer>> list = getDepartmentCostsAcs();
        Map<String, Integer> mapMin = new TreeMap<>();
        Integer min = list.get(0).getValue();
        String keyMin = list.get(0).getKey();
        for (Map.Entry<String, Integer> entry : list) {
            if (entry.getValue() < min) {
                min = entry.getValue();
                keyMin = entry.getKey();
            }
        }
        mapMin.put(keyMin, min);
        return mapMin;
    }
}
