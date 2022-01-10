package com.coffeeshop.coffeeApp.payload.request;

public class OrderItemRequest {
    private String menuItem;
    private Integer quantity;

    public String getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(String menuItem) {
        this.menuItem = menuItem;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
