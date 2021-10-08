package ru.julia.salarySpringLiquibase2.entities;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "departments2")
@Data
public class Department {
    @Column(name = "department_id")
    @Id
    private Integer id;
    @Column(name = "department")
    private String department;

    public Department(){
    }
}
