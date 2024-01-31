package com.myBudget.myBudget.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double salary;
    private Integer food_rate;
    private Integer transport_rate;
    private Integer sport_rate;
    private Integer invoice_rate;
    private Integer shopping_rate;
    private Integer leisure_rate;
    private Integer real_estate_rate;

    // Constructor
    public Forecast(Integer id, Double salary, Integer food_rate, Integer transport_rate, Integer sport_rate,
                Integer invoice_rate, Integer shopping_rate, Integer leisure_rate, Integer real_estate_rate) {
    this.id = id;
    this.salary = salary;
    this.food_rate = food_rate;
    this.transport_rate = transport_rate;
    this.sport_rate = sport_rate;
    this.invoice_rate = invoice_rate;
    this.shopping_rate = shopping_rate;
    this.leisure_rate = leisure_rate;
    this.real_estate_rate = real_estate_rate;
    }

    // Constructor par défaut
    public Forecast() {
    }

    // Getters pour accéder aux propriétés
    public Integer getId() { return id; }
    public Double getSalary() { return salary; }
    public Integer getFoodRate() { return food_rate; }
    public Integer getTransportRate() { return transport_rate; }
    public Integer getSportRate() { return sport_rate; }
    public Integer getInvoiceRate() { return invoice_rate; }
    public Integer getShoppingRate() { return shopping_rate; }
    public Integer getLeisureRate() { return leisure_rate; }
    public Integer getRealEstateRate() { return real_estate_rate; }

    // Setters pour afficher les propriétés
    public void setId(Integer id) { this.id = id; }
    public void setSalary(Double salary) { this.salary = salary; }
    public void setFoodRate(Integer food_rate) { this.food_rate = food_rate; }
    public void setTransportRate(Integer transport_rate) { this.transport_rate = transport_rate; }
    public void setSportRate(Integer sport_rate) { this.sport_rate = sport_rate; }
    public void setInvoiceRate(Integer invoice_rate) { this.invoice_rate = invoice_rate; }
    public void setShoppingRate(Integer shopping_rate) { this.shopping_rate = shopping_rate; }
    public void setLeisureRate(Integer leisure_rate) { this.leisure_rate = leisure_rate; }
    public void setRealEstateRate(Integer real_estate_rate) { this.real_estate_rate = real_estate_rate; }
}

