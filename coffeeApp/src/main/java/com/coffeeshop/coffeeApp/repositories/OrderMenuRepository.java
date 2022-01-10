package com.coffeeshop.coffeeApp.repositories;

import com.coffeeshop.coffeeApp.entities.OrderMenu;
import com.coffeeshop.coffeeApp.entities.keys.OrderMenuKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMenuRepository extends JpaRepository<OrderMenu, OrderMenuKey> {

}
