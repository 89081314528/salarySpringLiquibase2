package ru.julia.salarySpringLiquibase2.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.julia.salarySpringLiquibase2.entities.Kpi;
import ru.julia.salarySpringLiquibase2.services.KpiService;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class KpiController {
    private final KpiService kpiService;

    @RequestMapping("/findAllKpi")
    public List<Kpi> findAllKpi() {
        return kpiService.findAllKPI();
    }

    @RequestMapping("/fillTableKpi/{fileName}")
    public void fillTableKpiFromCsv(@PathVariable String fileName) throws FileNotFoundException {
        kpiService.fillTableKpiFromCsv(fileName);
    }
}
