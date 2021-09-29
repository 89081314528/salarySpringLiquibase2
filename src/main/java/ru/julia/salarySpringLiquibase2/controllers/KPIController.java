package ru.julia.salarySpringLiquibase2.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.julia.salarySpringLiquibase2.entities.KPI;
import ru.julia.salarySpringLiquibase2.entities.Salary;
import ru.julia.salarySpringLiquibase2.services.KPIService;
import ru.julia.salarySpringLiquibase2.services.SalaryService;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class KPIController {
    private final KPIService kpiService;

    @RequestMapping("/findAllKPI")
    public List<KPI> findAllKPI() {
        return kpiService.findAllKPI();
    }

    @RequestMapping("/fillTableKPI/{fileName}")
    public void fillTableKPIFromCsv(@PathVariable String fileName) throws FileNotFoundException {
        kpiService.fillTableKPIFromCsv(fileName);
    }
}
