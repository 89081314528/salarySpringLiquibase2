package ru.julia.salarySpringLiquibase2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.julia.salarySpringLiquibase2.entities.Salary;

import java.util.List;
import java.util.Optional;

public interface SalaryRepository extends JpaRepository<Salary, Integer> {
    @Override
    Optional<Salary> findById(Integer integer);

    List<Salary> findByName(String name);

    //    select
//    salary0_.salary_id as salary_i1_2_,
//    salary0_.department_id as departme2_2_,
//    salary0_.month as month3_2_,
//    salary0_.name as name4_2_,
//    salary0_.position as position5_2_,
//    salary0_.salary as salary6_2_
//            from
//    salaries3 salary0_
//    where
//    salary0_.name=?
//
//    select * from salaries3 where name = ?;
    List<Salary> getByDepartmentId(Integer departmentId); // select * from salaries3 where departmentId = ?;
    List<Salary> getByDepartmentIdAndSalary(Integer departmentId, Integer salary); // select * from salaries3 where departmentId = ? and salary = ?;
    List<Salary> getByDepartmentIdGreaterThanEqualAndSalaryGreaterThanEqual(Integer departmentId, Integer salary); // select * from salaries3
    // where departmentId >= ? and salary >= ?;
    List<Salary> getByDepartmentIdOrSalary(Integer departmentId, Integer salary); // select * from salaries3 where departmentId = ? or salary = ?;
//    Выхода два . Первый - сделать другую энтити в которой будут только те поля которые тебе нужны
//    Выход второй: Нужно сделать интерфейс, В котором будут геттеры для полей, которые тебе нужны
//    И вернуть лист этих интерфейсов, Реализацию интерфейса спринг сделает сам
}
