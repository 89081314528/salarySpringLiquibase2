package ru.julia.salarySpringLiquibase2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.julia.salarySpringLiquibase2.dto.SalaryAndKPI;
import ru.julia.salarySpringLiquibase2.dto.TotalComparator;
import ru.julia.salarySpringLiquibase2.entities.KPI;
import ru.julia.salarySpringLiquibase2.entities.Salary;
import ru.julia.salarySpringLiquibase2.entities.Total;
import ru.julia.salarySpringLiquibase2.repositories.KPIRepository;
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
    private final KPIRepository kpiRepository;

    @Override
    public List<Total> findAllTotals() {
        return totalRepository.findAll();
    }

    @Override
    public void getTotalCsvAndFillTable(String fileName) throws FileNotFoundException {
        List<Salary> salaries = salaryRepository.findAll();
        List<KPI> kpis = kpiRepository.findAll();
        Map<Integer, SalaryAndKPI> map = new HashMap<>();
        for (int i = 0; i < salaries.size(); i++) {
            Integer key = salaries.get(i).getId();
            Salary currentSalary = salaries.get(i);
            SalaryAndKPI salaryAndKPI = new SalaryAndKPI(currentSalary, null);
            map.put(key, salaryAndKPI);
        }

        for (int i = 0; i < kpis.size(); i++) {
            Integer key = kpis.get(i).getId();
            KPI kpi = kpis.get(i);
            Salary salary = map.get(key).getSalary();
            SalaryAndKPI salaryAndKPI = new SalaryAndKPI(salary, kpi);
            map.put(key, salaryAndKPI);
        }

        PrintStream csv = new PrintStream(fileName);
        csv.println(
                "name" + ";" + "salary" + ";" + "kpi" + ";" + "total" + ";" + "id"
        );
        for (SalaryAndKPI value : map.values()) {
            Integer sum = value.getSalary().getSalary() + value.getKpi().getKpi();
            Total total = new Total(
                    value.getSalary().getName(),
                    value.getSalary().getSalary(),
                    value.getKpi().getKpi(),
                    sum,
                    value.getSalary().getId());

            csv.println(
                    total.getName() + ";" +
                            total.getSalary() + ";" +
                            total.getKpi() + ";" +
                            total.getTotal() + ";" +
                            total.getId()
            );

            totalRepository.save(total);
        }
    }

    @Override
    public List<Total> sortAsc() {
        List<Total> totals = totalRepository.findAll();
        Comparator totalComparator = new TotalComparator();
        Collections.sort(totals, totalComparator);
        return totals;
    }

    @Override
    public Integer totalSum() {
        List<Total> totals = totalRepository.findAll();
        Integer sum = 0;
        for (Total total : totals) {
            sum = sum + total.getTotal();
        }
    }
}
