package ru.julia.salarySpringLiquibase2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.julia.salarySpringLiquibase2.entities.StaffingTable;

public interface StaffingTableRepository extends JpaRepository<StaffingTable, Integer> {
}
