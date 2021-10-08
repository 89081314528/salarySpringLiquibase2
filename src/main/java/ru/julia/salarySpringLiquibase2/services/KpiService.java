package ru.julia.salarySpringLiquibase2.services;

import ru.julia.salarySpringLiquibase2.entities.Kpi;

import java.io.FileNotFoundException;
import java.util.List;

public interface KpiService {
    public List<Kpi> findAllKPI();
    public void fillTableKpiFromCsv(String fileName) throws FileNotFoundException;
}
