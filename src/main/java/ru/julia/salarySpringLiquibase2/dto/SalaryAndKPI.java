package ru.julia.salarySpringLiquibase2.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.julia.salarySpringLiquibase2.entities.Kpi;
import ru.julia.salarySpringLiquibase2.entities.Salary;

@Data
@RequiredArgsConstructor
public class SalaryAndKPI {
    private final Salary salary;
    private final Kpi kpi;
}
