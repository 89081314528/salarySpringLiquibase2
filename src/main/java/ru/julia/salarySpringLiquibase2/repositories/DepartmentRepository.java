package ru.julia.salarySpringLiquibase2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.julia.salarySpringLiquibase2.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
