package ru.julia.salarySpringLiquibase2.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class TotalWithDepartment {
    private Integer totalId;
    private Integer salaryId;
    private String name;
    private Integer salary;
    private Integer kpi;
    private Integer total;
    private Integer departmentId;

    public TotalWithDepartment(Integer totalId, Integer salaryId, String name,
                               Integer salary, Integer kpi, Integer total, Integer departmentId) {
        this.totalId = totalId;
        this.salaryId = salaryId;
        this.name = name;
        this.salary = salary;
        this.kpi = kpi;
        this.total = total;
        this.departmentId = departmentId;
    }
}
