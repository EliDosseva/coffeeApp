package com.coffeeshop.coffeeApp.repositories;

import com.coffeeshop.coffeeApp.entities.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu findMenuByItem(String name);

    @Query("SELECT m.item, m.flavor, a" +
            " FROM Menu m join m.addIns a " +
            "WHERE LOWER(m.item)" +
            "LIKE :#{#menuItem.isEmpty()? '%' : #menuItem +'%'}" +
            " group by m.item, m.flavor, a.id")
    Page<Menu> filterMenuPages(Pageable pageable, String menuItem);
}
