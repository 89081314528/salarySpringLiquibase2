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
    @Column(name = "name")
    @CsvBindByName(column = "name")
    private String name;
    @Column(name = "salary")
    @CsvBindByName(column = "salary")
    private Integer salary;
    @CsvBindByName(column = "id")
    @Column(name = "id")
    @Id
    private Integer id;

    public Salary() {
    }
}
