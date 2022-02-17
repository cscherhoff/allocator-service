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

    public List<Allocation> getAllocations(long userId) {
        return allocationRepository.findAllByUserId(userId);
    }

    public void updateAllocations(long userId, List<Allocation> allocations) {
        allocations.forEach(allocation -> allocation.setUserId(userId));
        allocationRepository.deleteAll();
        allocationRepository.saveAll(allocations);
    }
}
