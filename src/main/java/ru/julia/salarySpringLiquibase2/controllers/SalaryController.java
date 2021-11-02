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
    public List<Salary> findAllSalaries() {
        return salaryService.findAllSalaries();
    }

    @RequestMapping("/fillTableSalary/{fileName}")
    public void fillTableSalaryFromCsv(@PathVariable String fileName) throws FileNotFoundException {
        salaryService.fillTableSalaryFromCsv(fileName);
    }

    @RequestMapping("/findByName/{name}")
    public void findByName(@PathVariable String name) throws FileNotFoundException {
        salaryService.findByName(name);
    }
}

