package ru.julia.salarySpringLiquibase2.entities;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "staffing_table")
@Data
public class StaffingTable {
    @Column(name = "staffing_id")
    @Id
    private Integer id;
    @Column(name = "position")
    private String position;
    @Column(name = "plan")
    private Integer plan;
    @Column(name = "fact")
    private Integer fact;

    public StaffingTable(Integer id, String position, Integer plan, Integer fact) {
        this.id = id;
        this.position = position;
        this.plan = plan;
        this.fact = fact;
    }
    public StaffingTable() {
    }
}
