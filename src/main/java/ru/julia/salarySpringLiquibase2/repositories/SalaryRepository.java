package ru.julia.salarySpringLiquibase2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.julia.salarySpringLiquibase2.entities.Salary;

public interface SalaryRepository extends JpaRepository<Salary, Integer> {
}
