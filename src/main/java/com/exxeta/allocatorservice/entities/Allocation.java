package com.exxeta.allocatorservice.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Allocation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long userId;

    @Column
    private String destination;

    @Column
    private BigDecimal amount;

    public Allocation() {
    }

    public Allocation(long userId, String destination, BigDecimal amount) {
        this.userId = userId;
        this.destination = destination;
        this.amount = amount;
    }

    public String getDestination() {
        return destination;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
