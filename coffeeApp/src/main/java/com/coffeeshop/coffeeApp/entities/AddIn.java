package com.coffeeshop.coffeeApp.entities;


import javax.persistence.*;

@Entity
public class AddIn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public AddIn(String name) {
        this.name = name;
    }

    public AddIn() {
    }

    @Override
    public String toString() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
