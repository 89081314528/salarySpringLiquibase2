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
    @Column(name = "id")
    @CsvBindByName(column = "id")
    @Id
    private Integer id;

    public Total() {
    }


    public Total(String name, Integer salary, Integer kpi, Integer total, Integer id) {
        this.name = name;
        this.salary = salary;
        this.kpi = kpi;
        this.total = total;
        this.id = id;
    }
}
