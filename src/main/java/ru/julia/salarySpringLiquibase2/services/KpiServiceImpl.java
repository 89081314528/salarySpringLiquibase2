package ru.julia.salarySpringLiquibase2.services;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.julia.salarySpringLiquibase2.dto.KpiDto;
import ru.julia.salarySpringLiquibase2.entities.Kpi;
import ru.julia.salarySpringLiquibase2.repositories.KpiRepository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KpiServiceImpl implements KpiService {
    private final KpiRepository kpiRepository;

    @Override
    public List<Kpi> findAllKPI() {
        return kpiRepository.findAll();
    }

    @Override
    public void fillTableKpiFromCsv(String fileName) throws FileNotFoundException {
        FileReader fileReader = new FileReader(fileName);
        List<KpiDto> resultDto = new CsvToBeanBuilder(fileReader).
                withType(KpiDto.class).
                withSeparator(';').
                build().parse();
        List<Kpi> result = new ArrayList<>();
        for (KpiDto kpiDto : resultDto) {
            result.add(new Kpi(kpiDto.getKpiId(), kpiDto.getName(), kpiDto.getKpi()));
        }
            kpiRepository.saveAll(result);
    }
}
