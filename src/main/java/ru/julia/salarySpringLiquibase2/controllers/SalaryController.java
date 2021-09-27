package ru.julia.salarySpringLiquibase2.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.julia.salarySpringLiquibase2.entities.Salary;
import ru.julia.salarySpringLiquibase2.services.SalaryService;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SalaryController {
    private final SalaryService salaryService;

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/findAllSalaries")
    public List<Salary> findAll() {
        return salaryService.findAll();
    }

    @RequestMapping("/fillTable/{fileName}")
    public void fillTableFromCsv(@PathVariable String fileName) throws FileNotFoundException {
        salaryService.fillTableFromCsv(fileName);
    }
}
