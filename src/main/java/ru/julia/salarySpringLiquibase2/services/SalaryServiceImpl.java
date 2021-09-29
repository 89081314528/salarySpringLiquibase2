package ru.julia.salarySpringLiquibase2.services;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.julia.salarySpringLiquibase2.entities.Salary;
import ru.julia.salarySpringLiquibase2.repositories.SalaryRepository;

import java.io.FileNotFoundException;
import java.io.FileReader;
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
            List<Salary> result = new CsvToBeanBuilder(fileReader).
                    withType(Salary.class).
                    withSeparator(';').
                    build().parse();
            for (Salary salary : result) {
                salaryRepository.save(salary);
            }
        }
}
