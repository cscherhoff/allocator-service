package com.exxeta.allocatorservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
public class Allocation {

    @Id
    @Column(length = 100)
    @JsonIgnore
    private String userId;

    private BigDecimal investment;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(
            name = "allocation_fixcosts",
            joinColumns = @JoinColumn(name = "allocation_id", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(name = "fixcost_id", referencedColumnName = "id")
    )
    private List<FixCost> fixCosts = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(
            name = "allocation_categories",
            joinColumns = @JoinColumn(name = "allocation_id", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    private List<Category> categories = new ArrayList<>();

    public Allocation() {
        investment = BigDecimal.ZERO;
    }

    public Allocation(String userId) {
        this();
        this.userId = userId;
    }

    public Allocation(String userId, BigDecimal investment) {
        this.userId = userId;
        this.investment = investment;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getInvestment() {
        return investment;
    }

    public List<FixCost> getFixCosts() {
        return fixCosts;
    }

    public List<Category> getCategories() {
        return categories;
    }

}
