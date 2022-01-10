package com.coffeeshop.coffeeApp.repositories;

import com.coffeeshop.coffeeApp.entities.CoffeeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<CoffeeUser, Long> {

    @Query("SELECT c " +
            "FROM CoffeeUser c " +
            "WHERE " +
            "lower(c.firstName) " +
            "LIKE :#{#firstName == null || #firstName.isEmpty()? '%' : #firstName +'%'} " +
            "AND lower(c.lastName)" +
            "LIKE :#{#lastName == null || #lastName.isEmpty()? '%' : #lastName +'%'}")
    List<CoffeeUser> findCoffeeUserByFirstNameAndLastName(String firstName, String lastName);

    CoffeeUser findCoffeeUserByUsername(String username);




}
