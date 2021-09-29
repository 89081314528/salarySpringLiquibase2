package ru.julia.salarySpringLiquibase2.services;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.julia.salarySpringLiquibase2.entities.KPI;
import ru.julia.salarySpringLiquibase2.entities.Salary;
import ru.julia.salarySpringLiquibase2.repositories.KPIRepository;
import ru.julia.salarySpringLiquibase2.repositories.SalaryRepository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KPIServiceImpl implements KPIService{
    private final KPIRepository kpiRepository;

    @Override
    public List<KPI> findAllKPI() {
        return kpiRepository.findAll();
    }

    @Override
    public void fillTableKPIFromCsv(String fileName) throws FileNotFoundException {
        FileReader fileReader = new FileReader(fileName);
        List<KPI> result = new CsvToBeanBuilder(fileReader).
                withType(KPI.class).
                withSeparator(';').
                build().parse();
        for (KPI kpi : result) {
            kpiRepository.save(kpi);
        }
    }
}
