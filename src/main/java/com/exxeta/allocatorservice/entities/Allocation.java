package com.exxeta.allocatorservice.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
public class Allocation {

    @Id
    @Column(length = 100)
    private String userId;

    private BigDecimal investment;

    @OneToMany(fetch = FetchType.EAGER)
    private List<FixCost> fixCosts = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
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

//    public Allocation(String userid, BigDecimal investment, List<FixCost> fixCosts, BigDecimal categories) {
//        this.userId = userId;
//        this.investment = investment;
//        this.fixCosts = fixCosts;
//        this.categories = categories;
//    }

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
