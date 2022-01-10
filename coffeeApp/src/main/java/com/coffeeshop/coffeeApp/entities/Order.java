package com.coffeeshop.coffeeApp.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private CoffeeUser coffeeUser;

    private Timestamp orderDate;

    @OneToMany(mappedBy = "order")
    private Set<OrderMenu> orderMenu;

    public Order() {
    }

    public Order(CoffeeUser coffeeUser, Timestamp orderDate) {
        this.coffeeUser = coffeeUser;
        this.orderDate = orderDate;
    }

    public Long getId() {
        return id;
    }

    public CoffeeUser getCoffeeUser() {
        return coffeeUser;
    }

    public void setCoffeeUser(CoffeeUser coffeeUser) {
        this.coffeeUser = coffeeUser;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Set<OrderMenu> getOrderMenu() {
        return orderMenu;
    }

    public void setOrderMenu(Set<OrderMenu> orderMenu) {
        this.orderMenu = orderMenu;
    }
}
