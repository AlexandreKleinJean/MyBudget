package com.myBudget.myBudget.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String gender;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Integer forecastId;

    // Constructor
    public Client(Integer id, String gender, String firstname, String lastname, String email, String password, Integer forecastId) {
        this.id = id;
        this.gender = gender;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.forecastId = forecastId;
    }

    // Constructor par défaut
    public Client() {
    }

    // Getters pour accéder aux propriétés
    public Integer getId() { return id; }
    public String getGender() { return gender; }
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Integer getForecastId() { return forecastId; }

    // Setters pour afficher les propriétés
    public void setId(Integer id) { this.id = id; }
    public void setGender(String gender) { this.gender = gender; }
    public void setFirstname(String Firstname) { this.firstname = Firstname; }
    public void setLastname(String lastname) { this.lastname = lastname; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setForecastId(Integer forecastId) { this.forecastId = forecastId; }
}
