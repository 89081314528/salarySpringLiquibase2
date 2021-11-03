package ru.julia.salarySpringLiquibase2.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.julia.salarySpringLiquibase2.entities.Salary;
import ru.julia.salarySpringLiquibase2.repositories.SalaryRepository;
import ru.julia.salarySpringLiquibase2.services.SalaryService;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SalaryController {
    private final SalaryService salaryService;
    private final SalaryRepository salaryRepository;

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
    public List<Salary> findByName(@PathVariable String name){
        return salaryService.findByName(name);
    }
    @RequestMapping("/getByDepartmentId/{departmentId}")
    public List<Salary> getByDepartmentId(@PathVariable Integer departmentId){
        return salaryRepository.getByDepartmentId(departmentId);
    }
    @RequestMapping("/getByDepartmentIdAndSalary/{departmentId}/{salary}")
    public List<Salary> getByDepartmentIdAndSalary(@PathVariable Integer departmentId,
                                                               @PathVariable Integer salary){
        return salaryRepository.getByDepartmentIdAndSalary(departmentId, salary);
    }

    @RequestMapping("/getByDepartmentIdGreaterThanEqualAndSalaryGreaterThanEqual/{departmentId}/{salary}")
    public List<Salary> getByDepartmentIdGreaterThanEqualAndSalaryGreaterThanEqual(@PathVariable Integer departmentId,
                                                   @PathVariable Integer salary){
        return salaryRepository.getByDepartmentIdGreaterThanEqualAndSalaryGreaterThanEqual(departmentId, salary);
    }
    @RequestMapping("/getByDepartmentIdOrSalary/{departmentId}/{salary}")
    public List<Salary> getByDepartmentIdOrSalary(@PathVariable Integer departmentId,
                                                   @PathVariable Integer salary){
        return salaryRepository.getByDepartmentIdOrSalary(departmentId, salary);
    }

}

