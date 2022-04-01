package com.exxeta.allocatorservice.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
public class Allocation {

    @Id
    private long userId;

    private BigDecimal investment;

    @OneToMany(fetch = FetchType.EAGER)
    private List<FixCost> fixCosts = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Category> categories = new ArrayList<>();

    public Allocation() {
        investment = BigDecimal.ZERO;
    }

    public Allocation(long userId) {
        this();
        this.userId = userId;
    }

    public Allocation(long userId, BigDecimal investment) {
        this.userId = userId;
        this.investment = investment;
    }

//    public Allocation(long userId, BigDecimal investment, List<FixCost> fixCosts, BigDecimal categories) {
//        this.userId = userId;
//        this.investment = investment;
//        this.fixCosts = fixCosts;
//        this.categories = categories;
//    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
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
