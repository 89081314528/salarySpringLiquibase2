package ru.julia.salarySpringLiquibase2.services;

import ru.julia.salarySpringLiquibase2.entities.Salary;

import java.io.FileNotFoundException;
import java.util.List;

public interface SalaryService {
    public List<Salary> findAllSalaries();
    public void fillTableSalaryFromCsv(String fileName) throws FileNotFoundException;

}

