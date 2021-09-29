package ru.julia.salarySpringLiquibase2.services;

import ru.julia.salarySpringLiquibase2.entities.KPI;
import ru.julia.salarySpringLiquibase2.entities.Salary;

import java.io.FileNotFoundException;
import java.util.List;

public interface KPIService {
    public List<KPI> findAllKPI();
    public void fillTableKPIFromCsv(String fileName) throws FileNotFoundException;
}
