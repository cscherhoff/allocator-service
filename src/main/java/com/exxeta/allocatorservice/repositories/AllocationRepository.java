package com.exxeta.allocatorservice.repositories;

import com.exxeta.allocatorservice.entities.Allocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Long> {
    List<Allocation> findAllByUserId(long userId);
}
