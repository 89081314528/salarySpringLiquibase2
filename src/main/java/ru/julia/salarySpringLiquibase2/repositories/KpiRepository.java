package ru.julia.salarySpringLiquibase2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.julia.salarySpringLiquibase2.entities.Kpi;

public interface KpiRepository extends JpaRepository<Kpi, Integer> {
}
