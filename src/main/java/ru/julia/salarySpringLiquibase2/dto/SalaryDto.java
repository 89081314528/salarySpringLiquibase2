package ru.julia.salarySpringLiquibase2.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class SalaryDto {
    @CsvBindByName(column = "salary_id") //в csv файле указываем название колонки salary_id, а не поля salaryId
    private Integer salaryId;
    @CsvBindByName(column = "name")
    private String name;
    @CsvBindByName(column = "salary")
    private Integer salary;
    @CsvBindByName(column = "department_id")
    private Integer departmentId;
    @CsvBindByName(column = "position")
    private String position;

    public SalaryDto(Integer salaryId, String name, Integer salary, Integer departmentId, String position) {
        this.salaryId = salaryId;
        this.name = name;
        this.salary = salary;
        this.departmentId = departmentId;
        this.position = position;
    }

    public SalaryDto() {
    }
}
