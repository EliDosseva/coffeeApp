package com.coffeeshop.coffeeApp.repositories;

import com.coffeeshop.coffeeApp.entities.AddIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AddInRepository extends JpaRepository<AddIn, Long> {
    AddIn findAddInByName(String name);

//    @Query("select a from AddIn a where lower(a.name) like :#{#name + '%'}")
//    Optional<AddIn> fetchAddIns(String name);
}
