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

    public Income getIncome() {
        return incomeRepository.findAll().get(0);
    }

    public void updateIncome(BigDecimal income) {
        incomeRepository.save(new Income(income));
    }
}
