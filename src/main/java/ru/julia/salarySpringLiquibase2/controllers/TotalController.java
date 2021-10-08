package ru.julia.salarySpringLiquibase2.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.julia.salarySpringLiquibase2.entities.Total;
import ru.julia.salarySpringLiquibase2.services.TotalService;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

/**
 * +Данные двух таблиц из csv файлов выгрузить в БД, объединить в третью таблицу Total, создать csv и заполнить БД
 * +отсортировать total по возрастанию зарплаты
 * +посчитать сумму
 * надо научиться делать как в реальности!!!!!!!!!!!!!!!!!!
 * +добавить отделы и ай ди отдела в класс сэлэри
 * * ??? как использовать внешние ключи
 * +показать общие затраты всех отделов по возрастанию затрат
 * +найти отделы, у которых наибольшие и наименьшие расходы на зарплату в месяц
 * отсортировать total сначала по отделу, и внутри отдела по возрастанию зарплаты
 * вывести зарплаты одного отдела по айди отдела, отсортированные по алфавиту и по сумме
 * найти три самых высоких зарплаты в каждом отделе
 * добавить должности. добавить штатное расписание. метод, который принимает нового сотрудника и добавляет его
 * в отдел, если его можно добавить. если нельзя добавить, вывести сообщение, что нельзя
 */
@RestController
@RequiredArgsConstructor
public class TotalController {
    private final TotalService totalService;

    @RequestMapping("/findAllTotals")
    public List<Total> findAllTotals() {
        return totalService.findAllTotals();
    }

    @RequestMapping("/makeTotalCsvAndFillTable/{fileName}")
    public void makeTotalCsvAndFillTable(@PathVariable String fileName) throws FileNotFoundException {
        totalService.makeTotalCsvAndFillTable(fileName);
    }
    @RequestMapping("/sortTotalsAsc")
    public List<Total> sortTotalsAsc() {
        return totalService.sortTotalAsc();
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
    public Map<String, Integer> getDepartmentWithMaxCosts() {
        return totalService.getDepartmentWithMaxCosts();
    }

    @RequestMapping("/getDepartmentWithMinCosts")
    public Map<String, Integer> getDepartmentWithMinCosts() {
        return totalService.getDepartmentWithMinCosts();
    }
}
