package ru.julia.salarySpringLiquibase2.services;

import ru.julia.salarySpringLiquibase2.entities.Salary;
import ru.julia.salarySpringLiquibase2.entities.Total;

import java.io.FileNotFoundException;
import java.util.List;

public interface TotalService {
    public List<Total> findAllTotals();
    public void getTotalCsvAndFillTable(String fileName) throws FileNotFoundException;
    public List<Total> sortAsc();
    public Integer totalSum();
}
