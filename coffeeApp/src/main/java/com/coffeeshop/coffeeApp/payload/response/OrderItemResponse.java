package com.coffeeshop.coffeeApp.payload.response;

import com.coffeeshop.coffeeApp.entities.AddIn;

import java.util.Set;

public class OrderItemResponse {
    private String item;
    private String flavor;
    private Set<AddIn> addIns;
    private Integer quantity;

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
