package com.myBudget.myBudget.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer number;
    private BigDecimal amount;
    private Integer userId;

    // Constructor
    public Account(Integer id, Integer number, BigDecimal amount, Integer userId) {
        this.id = id;
        this.number = number;
        this.amount = amount;
        this.userId = userId;
    };

    public Account(){
    };

    // Getter
    public Integer getId(){ return id; }
    public Integer getNumber(){ return number; }
    public BigDecimal getAmount(){ return amount; }
    public Integer getUserId(){ return userId; }

    // Setters
    public void setId(Integer id) { this.id = id; }
    public void setNumber(Integer number) { this.number = number; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public void setUserId(Integer userId) { this.userId = userId; }
}
