package com.myBudget.myBudget.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String bank;
    private Integer clientId;

    // Constructor
    public Account(Integer id, String name, String bank, Integer clientId) {
        this.id = id;
        this.name = name;
        this.bank = bank;
        this.clientId = clientId;
    };

    public Account(){
    };

    // Getter
    public Integer getId(){ return id; }
    public String getName(){ return name; }
    public String getBank(){ return bank; }
    public Integer getClientId(){ return clientId; }

    // Setters
    public void setId(Integer id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setBank(String bank) { this.bank = bank; }
    public void setClientId(Integer clientId) { this.clientId = clientId; }
}
