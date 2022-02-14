package com.exxeta.allocatorservice.services;

import com.exxeta.allocatorservice.entities.Allocation;
import com.exxeta.allocatorservice.repositories.AllocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllocationService {

    private final AllocationRepository allocationRepository;

    public AllocationService(AllocationRepository allocationRepository) {
        this.allocationRepository = allocationRepository;
    }

    public List<Allocation> getAllocations() {
        return allocationRepository.findAll();
    }

    public void updateAllocations(List<Allocation> allocations) {
        allocationRepository.saveAll(allocations);
    }
}
