package ru.julia.salarySpringLiquibase2.services;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.julia.salarySpringLiquibase2.dto.SalaryDto;
import ru.julia.salarySpringLiquibase2.entities.Salary;
import ru.julia.salarySpringLiquibase2.entities.StaffingTable;
import ru.julia.salarySpringLiquibase2.repositories.SalaryRepository;
import ru.julia.salarySpringLiquibase2.repositories.StaffingTableRepository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SalaryServiceImpl implements SalaryService {
    private final SalaryRepository salaryRepository;
    private final StaffingTableRepository staffingTableRepository;

    @Override
    public List<Salary> findAllSalaries() {
        return salaryRepository.findAll();
    }

    @Override
    public void fillTableSalaryFromCsv(String fileName) throws FileNotFoundException {
        FileReader fileReader = new FileReader(fileName);
        List<SalaryDto> resultDto = new CsvToBeanBuilder(fileReader).
                withType(SalaryDto.class).
                withSeparator(';').
                build().parse();
        List<Salary> result = new ArrayList<>();
        for (SalaryDto salaryDto : resultDto) {
            result.add(new Salary(salaryDto.getSalaryId(), salaryDto.getName(), salaryDto.getSalary(),
                    salaryDto.getMonth(), salaryDto.getDepartmentId(), salaryDto.getPosition()));
        }
        salaryRepository.saveAll(result);
    }

    @Override
    public List<Salary> findByName(String name) {
        return salaryRepository.findByName(name);
    }

    @Override
    public void acceptEmployee(String positionToAdd) {
        Map<String, String> namesAndPositionsMap = new HashMap<>(); // создаем мапу ключ имя значение должность
        List<Salary> salaries = salaryRepository.findAll();
        for (Salary salary : salaries) {
            namesAndPositionsMap.put(salary.getName(), salary.getPosition());
        }
        List<String> positionsList = new ArrayList<>(); // из мапы делаем лист всех позиций (с повторами)
        positionsList.addAll(namesAndPositionsMap.values());
        Map<String, Integer> positionsMap = new HashMap<>();// из листа делаем мапу ключ - должность значение - факт. количество
        for (String s : positionsList) {
            if (positionsMap.containsKey(s)) {
                positionsMap.put(s, positionsMap.get(s) + 1);
            } else {
                positionsMap.put(s, 1);
            }
        }
        List<StaffingTable> staffingTableList = staffingTableRepository.findAll();//на основании мапы заполняем факт в ШР
        for (StaffingTable staffingTable : staffingTableList) {
            String currentPosition = staffingTable.getPosition();
            if (positionsMap.containsKey(currentPosition)) {
                staffingTable.setFact(positionsMap.get(currentPosition));
            }
        }
        String message = "";
        for (StaffingTable staffingTable : staffingTableList) { // если в ШР план больше факта, то добавляем новую должность
            if (positionToAdd.equals(staffingTable.getPosition())) {
                if (staffingTable.getPlan() > staffingTable.getFact()) {
                    message = "Можно добавить сотрудника";
                } else
                    message = "Сотрудника добавить нельзя";
            }
        }
        System.out.println(message);
    }

}
