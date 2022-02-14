package com.exxeta.allocatorservice.services;

import com.exxeta.allocatorservice.entities.Income;
import com.exxeta.allocatorservice.repositories.IncomeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class IncomeService {

    private final IncomeRepository incomeRepository;

    public IncomeService(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    public BigDecimal getIncome() {
        return incomeRepository.findAll().get(0).getIncome();
    }

    public void updateIncome(BigDecimal income) {
        Income incomeFromDb = incomeRepository.findAll().get(0);
        incomeFromDb.setIncome(income);
        incomeRepository.save(incomeFromDb);
    }
}
