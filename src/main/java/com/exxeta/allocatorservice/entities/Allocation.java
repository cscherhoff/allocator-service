package com.exxeta.allocatorservice.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Allocation {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long id;

    @Id
    private long userId;

    private BigDecimal investment;

    private BigDecimal fixCosts;

    private BigDecimal categories;

    public Allocation() {
        investment = BigDecimal.ZERO;
        fixCosts = BigDecimal.ZERO;
        categories = BigDecimal.ZERO;
    }

    public Allocation(long userId) {
        this();
        this.userId = userId;
    }

    public Allocation(long userId, BigDecimal investment, BigDecimal fixCosts, BigDecimal categories) {
        this.userId = userId;
        this.investment = investment;
        this.fixCosts = fixCosts;
        this.categories = categories;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public BigDecimal getInvestment() {
        return investment;
    }

    public BigDecimal getFixCosts() {
        return fixCosts;
    }

    public BigDecimal getCategories() {
        return categories;
    }
}
