package com.coffeeshop.coffeeApp.payload.request;

import com.coffeeshop.coffeeApp.entities.AddIn;

import java.util.List;

public class MenuRequest {
    private String itemName;
    private String flavor;
    private List<AddIn> addInName;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public List<AddIn> getAddInName() {
        return addInName;
    }

    public void setAddInName(List<AddIn> addInName) {
        this.addInName = addInName;
    }
}
