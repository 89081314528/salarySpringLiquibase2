package ru.julia.salarySpringLiquibase2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.julia.salarySpringLiquibase2.dto.*;
import ru.julia.salarySpringLiquibase2.entities.Department;
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

    // раздела ранее написанный метод на 3 метода - строит тоталы, принимает тоталы входным параметром и записывает
    // из них сиэсви, вызывает 2 предыдущих метода и заполняет базу данных
    @Override
    public List<Total> makeTotals() { //??? нормально что тут мапа или надо новую сущность сделать?
        List<Salary> salaries = salaryRepository.findAll();//??? норм, что в total нет месяца, он есть в salary
        List<Kpi> kpis = kpiRepository.findAll();
        List<Total> totals = new ArrayList<>();
        Map<Integer, SalaryAndKPI> map = new HashMap<>();
        for (int i = 0; i < salaries.size(); i++) {
            Salary currentSalary = salaries.get(i);
            Integer salaryId = currentSalary.getSalaryId();
            SalaryAndKPI salaryAndKPI = new SalaryAndKPI(currentSalary, null);
            map.put(salaryId, salaryAndKPI);
        }

        for (int i = 0; i < kpis.size(); i++) {
            Kpi kpi = kpis.get(i);
            Integer salaryId = kpi.getSalaryId();
            Salary salary = map.get(salaryId).getSalary();
            SalaryAndKPI salaryAndKPI = new SalaryAndKPI(salary, kpi);
            map.put(salaryId, salaryAndKPI);
        }

        for (SalaryAndKPI salaryAndKPI : map.values()) {
            Integer sum = salaryAndKPI.getSalary().getSalary() + salaryAndKPI.getKpi().getKpi();
            Total total = new Total(
                    salaryAndKPI.getSalary().getSalaryId(), //айди total такой же как айди salary.??? как сделать
                    salaryAndKPI.getSalary().getSalaryId(), // произвольный. можно сделать total без total_id
                    salaryAndKPI.getSalary().getName(),
                    salaryAndKPI.getSalary().getSalary(),
                    salaryAndKPI.getKpi().getKpi(),
                    sum);
            totals.add(total);
        }
        return totals;
    }

    @Override
    public void makeCsv() throws FileNotFoundException {//??? как передать параметром List<Total>
        List<Total> totals = makeTotals();
        PrintStream csv = new PrintStream("total.csv");
        csv.println(
                "total_id" + ";" + "salary_id" + ";" + "name" + ";" + "salary" + ";" + "kpi" + ";" + "total"
        );
        for (Total total : totals) {
            csv.println(
                    total.getTotalId() + ";" +
                            total.getSalaryId() + ";" +
                            total.getName() + ";" +
                            total.getSalary() + ";" +
                            total.getKpi() + ";" +
                            total.getTotal()
            );
        }
    }

    @Override
    public void fillTable() {
        List<Total> totals = makeTotals();
        totalRepository.saveAll(totals);
    }

    @Override
    public List<Total> sortTotalsByTotalAsc() {
        List<Total> totals = totalRepository.findAll();
        Comparator totalComparator = new TotalComparator();
        Collections.sort(totals, totalComparator);
        return totals;
    }

    @Override
    public List<TotalWithDepartment> sortTotalByDepartmentAndTotal() {
        List<Total> totalsSortedByTotal = sortTotalsByTotalAsc();
        List<TotalWithDepartment> totalsWithDepartmentSortedByTotal = new ArrayList<>();
        for (Total total : totalsSortedByTotal) {
            Integer salaryId = total.getSalaryId();
            Integer departmentId = salaryRepository.findById(salaryId).orElseThrow().getDepartmentId();
            totalsWithDepartmentSortedByTotal.add(new TotalWithDepartment(total.getTotalId(), salaryId,
                    total.getName(), total.getSalary(), total.getKpi(), total.getTotal(),
                    departmentId));
        }

        List <Integer> departmentIdAsc = new ArrayList<>();
        List<Department> departments = departmentRepository.findAll();
        for (Department department : departments) {
            departmentIdAsc.add(department.getId());
        }
        Collections.sort(departmentIdAsc);

        List<TotalWithDepartment> totalsWithDepartmentSortedByTotalAndDepartment = new ArrayList<>();
        for (Integer integer : departmentIdAsc) {
            for (TotalWithDepartment totalWithDepartment : totalsWithDepartmentSortedByTotal) {
                if (integer.equals(totalWithDepartment.getDepartmentId())) {
                    totalsWithDepartmentSortedByTotalAndDepartment.add(totalWithDepartment);
                }
            }
        }
        return totalsWithDepartmentSortedByTotalAndDepartment;
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
        Map<String, Integer> map = new TreeMap<>();
        // собрать все айди тотала в лист
        //salaryRepository.findAllById() сюда передаю лист с айди и будут за один раз найдены все салэри с таким айди
        // собираю все департайди в лист, чтобы получить все департементы из departmentReposotory
        //сделать еще 2 мапы (ключ тотал айди, департамент) и (ключ департмент айди ди значение департамент)
        for (Total total : totals) {
            Integer departmentId = salaryRepository.findById(total.getTotalId()).orElseThrow().getDepartmentId();
            String key = departmentRepository.findById(departmentId).orElseThrow().getDepartment(); // key название департамента
            // заменить верхние строки на поиск по мапе
            Integer value = total.getTotal();
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + value);
            } else {
                map.put(key, value);
            }
        }
        List<Map.Entry<String, Integer>> list = new ArrayList(map.entrySet());
        Comparator<Map.Entry<String, Integer>> mapValueComparator = new MapValueComparator();
        list.sort(mapValueComparator);
        return list;
    }

    @Override
    public Map.Entry<String, Integer> getDepartmentWithMaxCosts() {
        List<Map.Entry<String, Integer>> list = getDepartmentCostsAcs();
        return list.get(list.size() - 1);
    }

    @Override
    public Map.Entry<String, Integer> getDepartmentWithMinCosts() {
        List<Map.Entry<String, Integer>> list = getDepartmentCostsAcs();
        return list.get(0);
    }

    @Override
    public List<Salary> depSalarySortedBySalary(Integer departmentId) {
        List <Salary> salaries = salaryRepository.findAll();
        List <Salary> depSalary = new ArrayList<>();
        for (Salary salary : salaries) {
            if (salary.getDepartmentId().equals(departmentId)) {
                depSalary.add(salary);
            }
        }
        Comparator<Salary> salaryNameComparator = new SalaryNameComporator();
        depSalary.sort(salaryNameComparator);
        return depSalary;
    }
}
