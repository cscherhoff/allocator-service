package com.exxeta.allocatorservice.repositories;

import com.exxeta.allocatorservice.entities.Category;
import com.exxeta.allocatorservice.entities.FixCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryName(String categoryName);
}
