package com.exxeta.allocatorservice.services;

import com.exxeta.allocatorservice.entities.Allocation;
import com.exxeta.allocatorservice.entities.Category;
import com.exxeta.allocatorservice.entities.FixCost;
import com.exxeta.allocatorservice.repositories.AllocationRepository;
import com.exxeta.allocatorservice.repositories.CategoryRepository;
import com.exxeta.allocatorservice.repositories.FixCostRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class AllocationService {

    private final AllocationRepository allocationRepository;
    private final FixCostRepository fixCostRepository;
    private final CategoryRepository categoryRepository;
    private final IncomeService incomeService;

    public AllocationService(AllocationRepository allocationRepository, FixCostRepository fixCostRepository, CategoryRepository categoryRepository, IncomeService incomeService) {
        this.allocationRepository = allocationRepository;
        this.fixCostRepository = fixCostRepository;
        this.categoryRepository = categoryRepository;
        this.incomeService = incomeService;
    }

    public Allocation getAllocation(long userId) {
        return allocationRepository.findByUserId(userId).orElseGet(() -> new Allocation(userId));
    }

    public void validateAllocation(Allocation allocation) throws InvalidAllocationException {
        BigDecimal income = incomeService.getIncome(allocation.getUserId());

        final BigDecimal investment = allocation.getInvestment();
        if (investment.doubleValue() < 0.00) {
            throw new InvalidAllocationException("The value for investment must be at least zero, but is" + investment.doubleValue());
        }

        BigDecimal sumOfFixCosts = BigDecimal.ZERO;
        for (FixCost fixCost: allocation.getFixCosts()) {
            if (fixCost.getAmount().doubleValue()<=0.00) {
                throw new InvalidAllocationException("The value of the fix cost '" + fixCost.getFixCostName() +
                        "' must be over zero, but is " + fixCost.getAmount());
            }
            sumOfFixCosts = sumOfFixCosts.add(fixCost.getAmount());
        }

        BigDecimal sumOfCategories = BigDecimal.ZERO;
        for (Category category: allocation.getCategories()) {
            if (category.getBudget().doubleValue()<=0.00) {
                throw new InvalidAllocationException("The value of the category '" + category.getCategoryName() +
                        "' must be over zero, but is " + category.getBudget());
            }
            sumOfCategories = sumOfCategories.add(category.getBudget());
        }

        final BigDecimal totalValueOfAllocation = investment.add(sumOfFixCosts).add(sumOfCategories);
        if (totalValueOfAllocation.doubleValue() != income.doubleValue()) {
            throw new InvalidAllocationException("The sum of the values for investments, fix costs and categories must equal the income." +
                    "The value of the sum is " + totalValueOfAllocation + ", but the income is " + income);
        }
    }

    public List<Category> getUpdatedCategories(Allocation allocation) {
        List<Category> updatedCategories = new ArrayList<>();
        for (Category category: allocation.getCategories()) {
            final Optional<Category> categoryFromDbOptional = categoryRepository.findByCategoryName(category.getCategoryName());
            if (categoryFromDbOptional.isEmpty()) {
                updatedCategories.add(category);
            } else {
                final Category categoryFromDb = categoryFromDbOptional.get();
                if (categoryFromDb.getBudget().doubleValue() != category.getBudget().doubleValue()) {
                    updatedCategories.add(category);
                }
            }
        }
        return updatedCategories;
    }

    public void updateAllocation(long userId, Allocation allocation) throws InvalidAllocationException {
        allocation.setUserId(userId);

        updateFixCosts(allocation);
        updateCategories(allocation);

        final Optional<Allocation> allocationFromDbOptional = allocationRepository.findByUserId(userId);
        if (allocationFromDbOptional.isEmpty()) {
            allocationRepository.save(allocation);
        } else {
            final Allocation allocationFromDb = allocationFromDbOptional.get();
            allocationFromDb.getFixCosts().clear();
            allocationFromDb.getFixCosts().addAll(allocation.getFixCosts());
            allocationFromDb.getCategories().clear();
            allocationFromDb.getCategories().addAll(allocation.getCategories());
            allocationRepository.save(allocationFromDb);
        }
    }

    private void updateCategories(Allocation allocation) {
        List<Category> categoriesToDelete = new ArrayList<>();
        List<Category> categoriesFromDatabase = new ArrayList<>();
        for (Category category: allocation.getCategories()) {
            final Optional<Category> categoryFromDbOptional = categoryRepository.findByCategoryName(category.getCategoryName());
            if (categoryFromDbOptional.isEmpty()) {
                categoryRepository.save(category);
            } else {
                final Category categoryFromDb = categoryFromDbOptional.get();
                if (categoryFromDb.getBudget().doubleValue() != category.getBudget().doubleValue()) {
                    categoryFromDb.setBudget(category.getBudget());
                    categoryRepository.save(categoryFromDb);
                }
                categoriesToDelete.add(category);
                categoriesFromDatabase.add(categoryFromDb);
            }
        }
        allocation.getCategories().removeAll(categoriesToDelete);
        allocation.getCategories().addAll(categoriesFromDatabase);
    }

    private void updateFixCosts(Allocation allocation) {
        List<FixCost> fixCostsToDelete = new ArrayList<>();
        List<FixCost> fixCostsFromDatabase = new ArrayList<>();
        for (FixCost fixCost: allocation.getFixCosts()) {
            final Optional<FixCost> fixCostFromDbOptional = fixCostRepository.findByFixCostName(fixCost.getFixCostName());
            if (fixCostFromDbOptional.isEmpty()) {
                fixCostRepository.save(fixCost);
            } else {
                final FixCost fixCostFromDb = fixCostFromDbOptional.get();
                if (fixCostFromDb.getAmount().doubleValue() != fixCost.getAmount().doubleValue()) {
                    fixCostFromDb.setAmount(fixCost.getAmount());
                    fixCostRepository.save(fixCostFromDb);
                }
                fixCostsToDelete.add(fixCost);
                fixCostsFromDatabase.add(fixCostFromDb);
            }
        }
        allocation.getFixCosts().removeAll(fixCostsToDelete);
        allocation.getFixCosts().addAll(fixCostsFromDatabase);
    }
}
