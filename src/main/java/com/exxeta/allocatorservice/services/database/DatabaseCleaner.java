package com.exxeta.allocatorservice.services.database;

import com.exxeta.allocatorservice.repositories.AllocationRepository;
import com.exxeta.allocatorservice.repositories.CategoryRepository;
import com.exxeta.allocatorservice.repositories.FixCostRepository;
import com.exxeta.allocatorservice.repositories.IncomeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("cleardb")
public class DatabaseCleaner {

    private final AllocationRepository allocationRepository;
    private final CategoryRepository categoryRepository;
    private final FixCostRepository fixCostRepository;
    private final IncomeRepository incomeRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(DatabaseCleaner.class);

    public DatabaseCleaner(AllocationRepository allocationRepository, CategoryRepository categoryRepository, FixCostRepository fixCostRepository, IncomeRepository incomeRepository) {
        this.allocationRepository = allocationRepository;
        this.categoryRepository = categoryRepository;
        this.fixCostRepository = fixCostRepository;
        this.incomeRepository = incomeRepository;
    }

    @PostConstruct
    void setUpDatabase() {
        LOGGER.info("Clearing databases...");
        allocationRepository.deleteAll();
        categoryRepository.deleteAll();
        fixCostRepository.deleteAll();
        incomeRepository.deleteAll();
        LOGGER.info("... all databases are empty!");
    }
}
