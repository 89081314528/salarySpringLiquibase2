package ru.julia.salarySpringLiquibase2.dto;

import java.util.Comparator;

public class TotalWithDepartmentComparator implements Comparator<TotalWithDepartment> {
    @Override
    public int compare(TotalWithDepartment o1, TotalWithDepartment o2) {
        if (o1.getDepartmentId() < o2.getDepartmentId()) {
            return -1;
        } else if (o1.getDepartmentId() == o2.getDepartmentId()) {
            if (o1.getTotal() < o2.getTotal()) {
                return  -1;
            } else if (o1.getTotal() == o2.getTotal()) {
                return 0;
            } else return 1;
        } else return 1;
    }
}
