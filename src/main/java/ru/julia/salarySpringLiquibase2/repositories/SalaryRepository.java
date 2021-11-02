package ru.julia.salarySpringLiquibase2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.julia.salarySpringLiquibase2.entities.Salary;

import java.util.List;
import java.util.Optional;

public interface SalaryRepository extends JpaRepository<Salary, Integer> {
    @Override
    Optional<Salary> findById(Integer integer);
    List<Salary> findByName(String name);
}
