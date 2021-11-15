package ru.julia.salarySpringLiquibase2.services;

import ru.julia.salarySpringLiquibase2.dto.TotalWithDepartment;
import ru.julia.salarySpringLiquibase2.entities.Salary;
import ru.julia.salarySpringLiquibase2.entities.Total;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public interface TotalService {
    List<Total> findAllTotals();

    List<Total> makeTotals();

    void makeCsv() throws FileNotFoundException;

    public void fillTable();

    List<Total> sortTotalsByTotalAsc();

    List<TotalWithDepartment> sortTotalByDepartmentAndTotal();

    Integer getTotalSum();

    List<Map.Entry<String, Integer>> getDepartmentCostsAcs();

    Map.Entry<String, Integer> getDepartmentWithMaxCosts();

    Map.Entry<String, Integer> getDepartmentWithMinCosts();

    List<Salary> depSalarySortedAsc(Integer departmentId);
}
