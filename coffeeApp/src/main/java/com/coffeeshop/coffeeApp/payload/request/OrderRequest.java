package com.coffeeshop.coffeeApp.payload.request;

import java.util.List;

public class OrderRequest {
    private String username;
    private List<OrderItemRequest> orders;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<OrderItemRequest> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderItemRequest> orders) {
        this.orders = orders;
    }
}
