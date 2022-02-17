package com.exxeta.allocatorservice.repositories;

import com.exxeta.allocatorservice.entities.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
    Optional<Income> findByUserId(long userId);
}
