package ru.julia.salarySpringLiquibase2.services;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.julia.salarySpringLiquibase2.dto.SalaryDto;
import ru.julia.salarySpringLiquibase2.entities.Salary;
import ru.julia.salarySpringLiquibase2.repositories.SalaryRepository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaryServiceImpl implements SalaryService{
    private final SalaryRepository salaryRepository;

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
}
