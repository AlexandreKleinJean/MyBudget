package com.myBudget.myBudget.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String subject;
    private String note;
    private String category;
    private BigDecimal amount;
    private Integer accountId;

    // Constructor
    public Transaction(Integer id, String subject, String note, String category, BigDecimal amount, Integer accountId) {
        this.id = id;
        this.subject = subject;
        this.note = note;
        this.category = category;
        this.amount = amount;
        this.accountId = accountId;
    }

    // Constructeur par défaut
    public Transaction() {
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public String getSubject() { return subject; }
    public String getNote() { return note; }
    public String getCategory() { return category; }
    public BigDecimal getAmount() { return amount; }
    public Integer getAccountId() { return accountId; }

    public void setId(Integer id) { this.id = id; }
    public void setSubject(String subject) { this.subject = subject; }
    public void setNote(String note) { this.note = note; }
    public void setCategory(String category) { this.category = category; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public void setAccountId(Integer accountId) { this.accountId = accountId; }

}
