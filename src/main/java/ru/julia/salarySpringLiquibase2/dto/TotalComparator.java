package ru.julia.salarySpringLiquibase2.dto;

import ru.julia.salarySpringLiquibase2.entities.Total;

import java.util.Comparator;

public class TotalComparator implements Comparator<Total> {
    @Override
    public int compare(Total o1, Total o2) {
        return o1.getTotal() - o2.getTotal();
    }
}
