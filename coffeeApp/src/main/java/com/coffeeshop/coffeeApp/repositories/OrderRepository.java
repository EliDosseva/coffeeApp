package com.coffeeshop.coffeeApp.repositories;

import com.coffeeshop.coffeeApp.entities.AddIn;
import com.coffeeshop.coffeeApp.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
