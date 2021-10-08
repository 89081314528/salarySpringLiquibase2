package ru.julia.salarySpringLiquibase2.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class KpiDto {
    @CsvBindByName(column = "kpi_id")
    private Integer kpiId;
    @CsvBindByName(column = "name")
    private String name;
    @CsvBindByName(column = "kpi")
    private Integer kpi;

    public KpiDto(Integer kpiId, String name, Integer kpi) {
        this.kpiId = kpiId;
        this.name = name;
        this.kpi = kpi;
    }

    public KpiDto() {
    }
}
