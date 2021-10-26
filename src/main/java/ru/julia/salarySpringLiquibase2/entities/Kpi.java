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
    @Column(name = "kpi_id")
    @Id
    private Integer kpiId;
    @Column(name = "salary_id")
    private Integer salaryId;
    @Column(name = "name")
    private String name;
    @Column(name = "kpi")
    private Integer kpi;

    public Kpi(Integer kpiId, Integer salaryId, String name, Integer kpi) {
        this.kpiId = kpiId;
        this.salaryId = salaryId;
        this.name = name;
        this.kpi = kpi;
    }

    public Kpi() {
    }
}
