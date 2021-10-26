package ru.julia.salarySpringLiquibase2.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class KpiDto {
    @CsvBindByName(column = "kpi_id")
    private Integer kpiId;
    @CsvBindByName(column = "salary_id")
    private Integer salaryId;
    @CsvBindByName(column = "name")
    private String name;
    @CsvBindByName(column = "kpi")
    private Integer kpi;

    public KpiDto(Integer kpiId, Integer salaryId, String name, Integer kpi) {
        this.kpiId = kpiId;
        this.salaryId = salaryId;
        this.name = name;
        this.kpi = kpi;
    }

    public KpiDto() {
    }
}
