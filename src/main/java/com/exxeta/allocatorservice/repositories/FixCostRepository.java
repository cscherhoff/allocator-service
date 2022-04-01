package com.exxeta.allocatorservice.repositories;

import com.exxeta.allocatorservice.entities.FixCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FixCostRepository extends JpaRepository<FixCost, Long> {
    Optional<FixCost> findByFixCostName(String fixCost);
}
