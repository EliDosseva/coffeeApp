package com.coffeeshop.coffeeApp.payload.response;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderResponse {
    private String coffeeUserName;
    private Timestamp date;
    private List<OrderItemResponse> items = new ArrayList<>();

    public String getCoffeeUserName() {
        return coffeeUserName;
    }

    public void setCoffeeUserName(String coffeeUserName) {
        this.coffeeUserName = coffeeUserName;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public List<OrderItemResponse> getItems() {
        return items;
    }

    public void setItems(List<OrderItemResponse> items) {
        this.items = items;
    }
}
