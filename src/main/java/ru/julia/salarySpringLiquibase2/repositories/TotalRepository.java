package ru.julia.salarySpringLiquibase2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.julia.salarySpringLiquibase2.entities.Total;

public interface TotalRepository extends JpaRepository<Total, Integer> {
}
