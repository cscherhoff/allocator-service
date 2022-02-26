package com.exxeta.allocatorservice.services;

import com.exxeta.allocatorservice.entities.Allocation;
import com.exxeta.allocatorservice.repositories.AllocationRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AllocationService {

    private final AllocationRepository allocationRepository;
    private final IncomeService incomeService;

    public AllocationService(AllocationRepository allocationRepository, IncomeService incomeService) {
        this.allocationRepository = allocationRepository;
        this.incomeService = incomeService;
    }

    public Allocation getAllocation(long userId) {
        return allocationRepository.findById(userId).orElseGet(() -> new Allocation(userId));
    }

    public void updateAllocation(long userId, Allocation allocation) throws InvalidAllocationException {
        allocationValidation(userId, allocation);
        allocation.setUserId(userId);
        allocationRepository.deleteAll();
        allocationRepository.save(allocation);
    }

    private void allocationValidation(long userId, Allocation allocation) throws InvalidAllocationException {
        BigDecimal income = incomeService.getIncome(userId);
        final BigDecimal investment = allocation.getInvestment();
        final BigDecimal fixCosts = allocation.getFixCosts();
        final BigDecimal categories = allocation.getCategories();
        final BigDecimal totalValueOfAllocation = investment.add(fixCosts).add(categories);
        if (investment.doubleValue() < 0.00) {
            throw new InvalidAllocationException("The value for investment must be at least zero, but is" + investment.doubleValue());
        } else if (fixCosts.doubleValue() < 0.00){
            throw new InvalidAllocationException("The value for fix costs must be at least zero, but is" + fixCosts.doubleValue());
        } else if (categories.doubleValue() < 0.00) {
            throw new InvalidAllocationException("The value for categories must be at least zero, but is" + categories.doubleValue());
        } else if (totalValueOfAllocation.doubleValue() > income.doubleValue()) {
            throw new InvalidAllocationException("The sum of the values for investments, fix costs and categories must not be greater tha the income." +
                    "The value of the sum is " + totalValueOfAllocation + " and the income is " + income);
        }
    }
}
