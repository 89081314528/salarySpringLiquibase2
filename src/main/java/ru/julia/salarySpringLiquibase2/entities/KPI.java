package ru.julia.salarySpringLiquibase2.entities;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "kpis")
@Data
public class KPI {
    @Column(name = "name")
    @CsvBindByName(column = "name")
    private String name;
    @Column(name = "kpi")
    @CsvBindByName(column = "kpi")
    private Integer kpi;
    @CsvBindByName(column = "id")
    @Column(name = "id")
    @Id
    private Integer id;

    public KPI() {
    }
}
