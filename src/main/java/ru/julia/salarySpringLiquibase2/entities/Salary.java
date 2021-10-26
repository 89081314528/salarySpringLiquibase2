package ru.julia.salarySpringLiquibase2.entities;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "salaries3")
@Data
public class Salary {
    @Column(name = "salary_id")
    @Id
    private Integer salaryId;
    @Column(name = "name")
    private String name;
    @Column(name = "salary")
    private Integer salary;
    @Column(name = "month")
    private Integer month;
    @Column(name = "department_id")
    private Integer departmentId;
    @Column(name = "position")
    private String position;

    public Salary(Integer salaryId, String name, Integer salary, Integer month, Integer departmentId, String position) {
        this.salaryId = salaryId;
        this.name = name;
        this.salary = salary;
        this.month = month;
        this.departmentId = departmentId;
        this.position = position;
    }

    public Salary() {
    }
}
