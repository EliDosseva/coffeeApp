package com.coffeeshop.coffeeApp.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String item;
    private String flavor;

    @ManyToMany
    @JoinTable(
            name = "menu_addIn",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "addIn_id")
    )
    Set<AddIn> addIns;

    public Menu(String item, String flavor, Set<AddIn> addIns) {
        this.item = item;
        this.flavor = flavor;
        this.addIns = addIns;
    }

    public Menu() {
    }

    public Long getId() {
        return id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public Set<AddIn> getAddIns() {
        return addIns;
    }

    public void setAddIns(Set<AddIn> addIns) {
        this.addIns = addIns;
    }

}
