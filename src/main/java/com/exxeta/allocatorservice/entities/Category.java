package com.exxeta.allocatorservice.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "allocation_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    private String categoryName;

    private BigDecimal budget;

    public Category() {

    }

    public long getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }
}
