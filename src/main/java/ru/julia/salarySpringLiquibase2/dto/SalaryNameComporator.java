package ru.julia.salarySpringLiquibase2.dto;

import ru.julia.salarySpringLiquibase2.entities.Salary;
import ru.julia.salarySpringLiquibase2.entities.Total;

import java.util.Comparator;

public class SalaryNameComporator implements Comparator<Salary> {
    @Override
    public int compare(Salary o1, Salary o2) {
        return o1.getSalary().compareTo(o2.getSalary());
    }
}
