package ru.julia.salarySpringLiquibase2.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.julia.salarySpringLiquibase2.dto.NameAndSalary;
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
    public List<Salary> findByName(@PathVariable String name) {
        return salaryService.findByName(name);
    }

    @RequestMapping("/getByDepartmentId/{departmentId}")
    public List<Salary> getByDepartmentId(@PathVariable Integer departmentId) {
        return salaryRepository.getByDepartmentId(departmentId);
    }

    @RequestMapping("/getByDepartmentIdAndSalary/{departmentId}/{salary}")
    public List<Salary> getByDepartmentIdAndSalary(@PathVariable Integer departmentId,
                                                   @PathVariable Integer salary) {
        return salaryRepository.getByDepartmentIdAndSalary(departmentId, salary);
    }

    @RequestMapping("/getByDepartmentIdGreaterThanEqualAndSalaryGreaterThanEqual/{departmentId}/{salary}")
    public List<Salary> getByDepartmentIdGreaterThanEqualAndSalaryGreaterThanEqual(@PathVariable Integer departmentId,
                                                                                   @PathVariable Integer salary) {
        return salaryRepository.getByDepartmentIdGreaterThanEqualAndSalaryGreaterThanEqual(departmentId, salary);
    }

    @RequestMapping("/getByDepartmentIdOrSalary/{departmentId}/{salary}")
    public List<Salary> getByDepartmentIdOrSalary(@PathVariable Integer departmentId,
                                                  @PathVariable Integer salary) {
        return salaryRepository.getByDepartmentIdOrSalary(departmentId, salary);
    }

    @RequestMapping("/getNameAndSalaryByDepartmentId/{departmentId}")
    public List<NameAndSalary> getNameAndSalaryByDepartmentId(@PathVariable Integer departmentId) {
        return salaryRepository.getNameAndSalaryByDepartmentId(departmentId);
    }

    @RequestMapping("/findByNameLike/{name}")
    public List<Salary> findByNameLike(@PathVariable String name) {
        return salaryRepository.findByNameLike(name = "%" + name + "%");
    }

    @RequestMapping("/findByNameStartingWith/{name}")
    public List<Salary> findByNameStartingWith(@PathVariable String name) {
        return salaryRepository.findByNameStartingWith(name);
    }

    @RequestMapping("/findByNameContaining/{name}")
    public List<Salary> findByNameContaining(@PathVariable String name) {
        return salaryRepository.findByNameContaining(name);
    }

    @RequestMapping("/findByDepartmentIdOrderByNameDesc/{departmentId}")
    public List<Salary> findByDepartmentIdOrderByNameDesc(@PathVariable Integer departmentId) {
        return salaryRepository.findByDepartmentIdOrderByNameDesc(departmentId);
    }

    @RequestMapping("/findByNameIgnoreCase/{name}")
    public List<Salary> findByNameIgnoreCase(@PathVariable String name) {
        return salaryRepository.findByNameIgnoreCase(name);
    }

    @RequestMapping("/count")
    public long count() {
        return salaryRepository.count();
    }

    @RequestMapping("/deleteByName/{name}")
    public long deleteByName(@PathVariable String name) {
        return salaryRepository.deleteByName(name);
    }

    @RequestMapping("/removeByName/{name}")
    public List<Salary> removeByName(@PathVariable String name) {
        return salaryRepository.removeByName(name);
    }

    @RequestMapping("/findByName2/{name}")
    public List<Salary> findByName2(@PathVariable String name) {
        return salaryRepository.findByName2(name);
    }

    @RequestMapping("/getByDepartmentIdGreaterThanEqualAndSalaryGreaterThanEqual2/{departmentId}/{salary}")
    public List<Salary> getByDepartmentIdGreaterThanEqualAndSalaryGreaterThanEqual2(@PathVariable Integer departmentId,
                                                                                    @PathVariable Integer salary) {
        return salaryRepository.getByDepartmentIdGreaterThanEqualAndSalaryGreaterThanEqual2(departmentId, salary);
    }

    @RequestMapping("/removeByName2/{name}")
    void removeByName2(@PathVariable String name) {
        salaryRepository.removeByName2(name);
    }


    @RequestMapping("/acceptEmployee/{positionToAdd}")
    public void acceptEmployee(@PathVariable String positionToAdd) {
        salaryService.acceptEmployee(positionToAdd);
    }
}
