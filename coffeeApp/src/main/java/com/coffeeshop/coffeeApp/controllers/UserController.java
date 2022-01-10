package com.coffeeshop.coffeeApp.controllers;

import com.coffeeshop.coffeeApp.entities.CoffeeUser;
import com.coffeeshop.coffeeApp.repositories.UserRepository;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RequestMapping("/user")
@RestController
@CrossOrigin(origins = "*")
public class UserController {

    private final UserRepository userRepo;

    UserController(UserRepository userRepository) {
        userRepo = userRepository;
    }

    @GetMapping("/fetch")
    private List<CoffeeUser> getAllUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/find/name")
    public ResponseEntity<?> findCoffeeUserByFirstNameAndLastName(String firstName, String lastName) {
        //null
        List<CoffeeUser> result = userRepo.findCoffeeUserByFirstNameAndLastName(firstName.toLowerCase(), lastName.toLowerCase());

        return ResponseEntity.ok(result.isEmpty() ? "Not Found!" : result);

    }

    @PostMapping("/save")
    public ResponseEntity<?> persistUser(String firstName, String lastName, String username, String email, String phoneNumber, String address){
        List<CoffeeUser> coffeeUsers = userRepo.findCoffeeUserByFirstNameAndLastName(firstName.toLowerCase(), lastName.toLowerCase());
        if(coffeeUsers.isEmpty()) {
            return ResponseEntity.ok(userRepo.save(new CoffeeUser(firstName, lastName, username, email, phoneNumber, address)));
        }
        return ResponseEntity.ok("User already exists!");
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(String firstName, String lastName, String username, String email, String phoneNumber, String address) {
        List<CoffeeUser> coffeeUsers = userRepo.findCoffeeUserByFirstNameAndLastName(firstName.toLowerCase(), lastName.toLowerCase());
        List<CoffeeUser> response = new ArrayList<>();
        if (!coffeeUsers.isEmpty()) {
            for (CoffeeUser coffeeUser : coffeeUsers) {
                coffeeUser.setEmail(email);
                coffeeUser.setUsername(username);
                coffeeUser.setPhoneNumber(phoneNumber);
                coffeeUser.setAddress(address);

                response.add(userRepo.save(coffeeUser));
            }
            return ResponseEntity.ok(response);
        }
        else
            return ResponseEntity.badRequest().body("User doesn't exist");
    }

    @DeleteMapping("/delete")
    public String deleteClient(String firstName, String lastName){
        List<CoffeeUser> result = userRepo.findCoffeeUserByFirstNameAndLastName(firstName.toLowerCase(), lastName.toLowerCase());
        if (result.isEmpty()){
            return "Client not found!";
        }
        for (CoffeeUser coffeeUser: result)
            userRepo.delete(coffeeUser);
        return firstName + " " + lastName + " deleted!";
    }
}
