package ru.julia.salarySpringLiquibase2.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.julia.salarySpringLiquibase2.dto.TotalWithDepartment;
import ru.julia.salarySpringLiquibase2.entities.Salary;
import ru.julia.salarySpringLiquibase2.entities.Total;
import ru.julia.salarySpringLiquibase2.services.TotalService;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

/**
 * +Данные двух таблиц из csv файлов выгрузить в БД, объединить в третью таблицу Total, создать csv и заполнить БД
 * +отсортировать total по возрастанию зарплаты - sortTotalsByTotalAsc
 * +посчитать сумму - getTotalSum
 * надо научиться делать как в реальности!!!!!!!!!!!!!!!!!!
 * +добавить отделы и ай ди отдела в класс сэлэри
 * * ??? как использовать внешние ключи
 * +показать общие затраты всех отделов по возрастанию затрат - getDepartmentCostsAcs
 * +найти отделы, у которых наибольшие и наименьшие расходы на зарплату в месяц - getDepartmentWithMaxCosts
 * +отсортировать total сначала по отделу, и внутри отдела по возрастанию зарплаты - sortTotalByDepartmentAndTotal,
 * !!!+использовать компоратор (сравнивает depId и если не равны то сравнивает total) sortTotalByDepartmentAndTotal2
 * +вывести зарплаты одного отдела по айди отдела, отсортированные по сумме - depSalarySortedAsc
 *
 * !!!!+добавить должности. добавить штатное расписание. метод, который принимает нового сотрудника и добавляет его
 * в отдел, если его можно добавить. если нельзя добавить, вывести сообщение, что нельзя
 * +в total добавить колонку salary id, чтобы связать все таблицы, сделать чтобы данные по зп и кпи были за несколько месяцев
 * +потренироваться создавать в salary репозитории методы и посмотреть какие запросы они формируют
 * посчитать количество символов в файле
 * мой эксель файл где я пишу сколько занимаюсь
 */
@RestController
@RequiredArgsConstructor
public class TotalController {
    private final TotalService totalService;

    @RequestMapping("/findAllTotals")
    public List<Total> findAllTotals() {
        return totalService.findAllTotals();
    }
    @RequestMapping("/makeTotals")
    public List<Total> makeTotals() {
        return totalService.makeTotals();
    }
    @RequestMapping("/makeCsv")
    public void makeCsv() throws FileNotFoundException {
        totalService.makeCsv();
    }
    @RequestMapping("/fillTable")
    public void fillTable() {
        totalService.fillTable();
    }

    @RequestMapping("/sortTotalsByTotalAsc")
    public List<Total> sortTotalsByTotalAsc() {
        return totalService.sortTotalsByTotalAsc();
    }

    @RequestMapping("/sortTotalByDepartmentAndTotal")
    public List<TotalWithDepartment> sortTotalByDepartmentAndTotal() {
        return totalService.sortTotalByDepartmentAndTotal();
    }

    @RequestMapping("/sortTotalByDepartmentAndTotal2")
    public List<TotalWithDepartment> sortTotalByDepartmentAndTotal2() {
        return totalService.sortTotalByDepartmentAndTotal2();
    }

    @RequestMapping("/getTotalSum")
    public Integer getTotalSum() {
        return totalService.getTotalSum();
    }

    @RequestMapping("/getDepartmentCostsAcs")
    public List<Map.Entry<String, Integer>> getDepartmentCostsAcs() {
        return totalService.getDepartmentCostsAcs();
    }

    @RequestMapping("/getDepartmentWithMaxCosts")
    public Map.Entry<String, Integer> getDepartmentWithMaxCosts() {
        return totalService.getDepartmentWithMaxCosts();
    }

    @RequestMapping("/getDepartmentWithMinCosts")
    public Map.Entry<String, Integer> getDepartmentWithMinCosts() {
        return totalService.getDepartmentWithMinCosts();
    }

    @RequestMapping("/depSalarySortedAsc/{departmentId}")
    public List<Salary> depSalarySortedAsc(@PathVariable Integer departmentId) {
        return totalService.depSalarySortedAsc(departmentId);
    }
}
