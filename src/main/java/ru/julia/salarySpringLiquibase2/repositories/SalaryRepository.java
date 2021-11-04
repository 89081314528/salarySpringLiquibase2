package ru.julia.salarySpringLiquibase2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.julia.salarySpringLiquibase2.dto.NameAndSalary;
import ru.julia.salarySpringLiquibase2.entities.Salary;

import java.util.List;
import java.util.Optional;

public interface SalaryRepository extends JpaRepository<Salary, Integer> {
    @Override
    Optional<Salary> findById(Integer integer);

    List<Salary> findByName(String name); // find и get одно и то же
//    select * from salaries3 where name = ?;
    List<Salary> getByDepartmentId(Integer departmentId); // select * from salaries3 where departmentId = ?;

    List<Salary> getByDepartmentIdAndSalary(Integer departmentId, Integer salary); // select * from salaries3 where departmentId = ? and salary = ?;

    List<Salary> getByDepartmentIdGreaterThanEqualAndSalaryGreaterThanEqual(Integer departmentId, Integer salary); // select * from salaries3
    // where departmentId >= ? and salary >= ?;
    List<Salary> getByDepartmentIdOrSalary(Integer departmentId, Integer salary); // select * from salaries3 where departmentId = ? or salary = ?;

    List<NameAndSalary> getNameAndSalaryByDepartmentId(Integer departmentId);
//  Нужно сделать интерфейс, В котором будут геттеры для полей, которые нужны (name, salary) и вернуть лист этих интерфейсов,
//  Реализацию интерфейса спринг сделает сам
//  select name, salary from salaries3 where departmentId = ?

    List<Salary> findByNameLike(String name); // select * from salaries3 where name like 'Александрова Екатерина Геннадьевна';
    // ищет полное соответствие, но если прописать в методе в контроллере
    // return salaryRepository.findByNameLike(name = "%" + name + "%"); то будет искать часть имени

    List<Salary> findByNameStartingWith(String name); //select * from salaries3 where name like 'Александрова%';

    List<Salary> findByNameContaining(String name); //select * from salaries3 where name like '%Екатерина%';

    List<Salary> findByDepartmentIdOrderByNameDesc(Integer departmentId); //select * from salaries3 where departmentId = ? order by name desc;

    List<Salary> findByNameIgnoreCase(String name);//select * from salaries3 where upper(name) = upper(?);
}
