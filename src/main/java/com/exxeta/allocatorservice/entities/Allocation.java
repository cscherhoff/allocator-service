package com.exxeta.allocatorservice.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Allocation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String destination;

    @Column
    private BigDecimal amount;
}
