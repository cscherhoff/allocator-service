package com.exxeta.allocatorservice.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class FixCost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    private String fixCostName;

    private BigDecimal amount;

    public FixCost() {
    }

    public FixCost(String fixCostName, BigDecimal amount) {
        this.fixCostName = fixCostName;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public String getFixCostName() {
        return fixCostName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
