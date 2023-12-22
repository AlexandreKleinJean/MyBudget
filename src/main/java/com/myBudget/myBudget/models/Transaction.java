package com.myBudget.myBudget.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String category;
    private Integer amount;
    private Integer accountId;

    // Constructor
    public Transaction(Integer id, Date date, String category, Integer amount, Integer accountId) {
        this.id = id;
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.accountId = accountId;
    };

    // Constructeur par défaut
    public Transaction(){
    }

    // Getters
    public Integer getId() { return id; }
    public Date getDate() { return date; }
    public String getCategory() { return category; }
    public Integer getAmount() { return amount; }
    public Integer getAccountId() { return accountId; }

    // Setters
    public void setId(Integer id) { this.id = id; }
    public void setDate(Date date) { this.date = date; }
    public void setCategory(String category) { this.category = category; }
    public void setAmount(Integer amount) { this.amount = amount; }
    public void setAccountId(Integer accountId) { this.accountId = accountId; }

}

