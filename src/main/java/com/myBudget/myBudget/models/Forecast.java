package com.myBudget.myBudget.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "forecast")
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double salary;
    private Integer foodRate;
    private Integer transportRate;
    private Integer sportRate;
    private Integer invoiceRate;
    private Integer shoppingRate;
    private Integer leisureRate;
    private Integer realEstateRate;

    // Constructor
    public Forecast(Integer id, Double salary, Integer foodRate, Integer transportRate, Integer sportRate,
                Integer invoiceRate, Integer shoppingRate, Integer leisureRate, Integer realEstateRate) {
    this.id = id;
    this.salary = salary;
    this.foodRate = foodRate;
    this.transportRate = transportRate;
    this.sportRate = sportRate;
    this.invoiceRate = invoiceRate;
    this.shoppingRate = shoppingRate;
    this.leisureRate = leisureRate;
    this.realEstateRate = realEstateRate;
    }

    // Constructor par défaut
    public Forecast() {
    }

    // Getters pour accéder aux propriétés
    public Integer getId() { return id; }
    public Double getSalary() { return salary; }
    public Integer getFoodRate() { return foodRate; }
    public Integer getTransportRate() { return transportRate; }
    public Integer getSportRate() { return sportRate; }
    public Integer getInvoiceRate() { return invoiceRate; }
    public Integer getShoppingRate() { return shoppingRate; }
    public Integer getLeisureRate() { return leisureRate; }
    public Integer getRealEstateRate() { return realEstateRate; }

    // Setters pour afficher les propriétés
    public void setId(Integer id) { this.id = id; }
    public void setSalary(Double salary) { this.salary = salary; }
    public void setFoodRate(Integer foodRate) { this.foodRate = foodRate; }
    public void setTransportRate(Integer transportRate) { this.transportRate = transportRate; }
    public void setSportRate(Integer sportRate) { this.sportRate = sportRate; }
    public void setInvoiceRate(Integer invoiceRate) { this.invoiceRate = invoiceRate; }
    public void setShoppingRate(Integer shoppingRate) { this.shoppingRate = shoppingRate; }
    public void setLeisureRate(Integer leisureRate) { this.leisureRate = leisureRate; }
    public void setRealEstateRate(Integer realEstateRate) { this.realEstateRate = realEstateRate; }
}

