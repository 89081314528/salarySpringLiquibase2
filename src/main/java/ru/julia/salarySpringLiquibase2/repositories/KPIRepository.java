package ru.julia.salarySpringLiquibase2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.julia.salarySpringLiquibase2.entities.KPI;

public interface KPIRepository extends JpaRepository<KPI, Integer> {
}
