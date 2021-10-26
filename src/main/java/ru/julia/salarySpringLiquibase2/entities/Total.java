package ru.julia.salarySpringLiquibase2.entities;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "totals")
@Data
public class Total {
    @Column(name = "total_id")
    @CsvBindByName(column = "total_id")
    @Id
    private Integer totalId;
    @Column(name = "salary_id")
    @CsvBindByName(column = "salary_id")
    private Integer salaryId;
    @Column(name = "name")
    @CsvBindByName(column = "name")
    private String name;
    @Column(name = "salary")
    @CsvBindByName(column = "salary")
    private Integer salary;
    @Column(name = "kpi")
    @CsvBindByName(column = "kpi")
    private Integer kpi;
    @Column(name = "total")
    @CsvBindByName(column = "total")
    private Integer total;

    public Total(Integer totalId, Integer salaryId, String name, Integer salary, Integer kpi, Integer total) {
        this.totalId = totalId;
        this.salaryId = salaryId;
        this.name = name;
        this.salary = salary;
        this.kpi = kpi;
        this.total = total;
    }

    public Total() {
    }

}
