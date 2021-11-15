package ru.julia.salarySpringLiquibase2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.julia.salarySpringLiquibase2.repositories.StaffingTableRepository;

@Service
@RequiredArgsConstructor
public class StaffingTableServiceImpl implements StaffingTableService{
    private final StaffingTableRepository staffingTableRepository;

    @Override
    public void fillFact() {
    }
}
