package ru.julia.salarySpringLiquibase2.dto;

import ru.julia.salarySpringLiquibase2.entities.Total;
import java.util.Comparator;

public class TotalComparator implements Comparator<Total> {
    @Override
    public int compare(Total total1, Total total2) {
        return total1.getTotal() - total2.getTotal();
    }
}
