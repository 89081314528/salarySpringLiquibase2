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
public class Kpi {
    @CsvBindByName(column = "kpi_id")
    @Column(name = "kpi_id")
    @Id
    private Integer kpiId;
    @Column(name = "name")
    @CsvBindByName(column = "name")
    private String name;
    @Column(name = "kpi")
    @CsvBindByName(column = "kpi")
    private Integer kpi;

    public Kpi(Integer kpiId, String name, Integer kpi) {
        this.kpiId = kpiId;
        this.name = name;
        this.kpi = kpi;
    }

    public Kpi() {
    }
}
