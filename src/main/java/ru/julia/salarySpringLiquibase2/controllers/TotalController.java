package ru.julia.salarySpringLiquibase2.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.julia.salarySpringLiquibase2.entities.Salary;
import ru.julia.salarySpringLiquibase2.entities.Total;
import ru.julia.salarySpringLiquibase2.services.TotalService;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TotalController {
    private final TotalService totalService;

    @RequestMapping("/findAllTotals")
    public List<Total> findAllTotals() {
        return totalService.findAllTotals();
    }

    @RequestMapping("/getTotalCsvAndFillTable/{fileName}")
    public void getTotalCsvAndFillTable(@PathVariable String fileName) throws FileNotFoundException {
        totalService.getTotalCsvAndFillTable(fileName);
    }

}
