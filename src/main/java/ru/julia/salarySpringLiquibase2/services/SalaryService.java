package ru.julia.salarySpringLiquibase2.services;

import ru.julia.salarySpringLiquibase2.entities.Salary;

import java.io.FileNotFoundException;
import java.util.List;

public interface SalaryService {
    List<Salary> findAllSalaries();
    void fillTableSalaryFromCsv(String fileName) throws FileNotFoundException;
    List<Salary> findByName(String name);
    void acceptEmployee(String position);
}

