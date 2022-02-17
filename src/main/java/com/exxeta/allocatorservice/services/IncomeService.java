package com.exxeta.allocatorservice.services;

import com.exxeta.allocatorservice.entities.Income;
import com.exxeta.allocatorservice.repositories.IncomeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class IncomeService {

    private final IncomeRepository incomeRepository;

    public IncomeService(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    public BigDecimal getIncome(long userId) {
        final Optional<Income> incomeFromDb = incomeRepository.findByUserId(userId);
        final Income income = incomeFromDb.orElse(new Income(BigDecimal.ZERO));
        return income.getIncome();
    }

    public void updateIncome(long userId, BigDecimal incomeValue) {
        final Optional<Income> incomeFromDb = incomeRepository.findByUserId(userId);
        final Income income = incomeFromDb.orElse(new Income(incomeValue));
        income.setIncome(incomeValue);

        incomeRepository.save(income);
    }
}
