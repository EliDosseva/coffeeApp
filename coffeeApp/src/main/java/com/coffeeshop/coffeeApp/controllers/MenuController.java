package com.coffeeshop.coffeeApp.controllers;

import com.coffeeshop.coffeeApp.entities.*;
import com.coffeeshop.coffeeApp.entities.keys.OrderMenuKey;
import com.coffeeshop.coffeeApp.payload.request.MenuRequest;
import com.coffeeshop.coffeeApp.payload.request.OrderItemRequest;
import com.coffeeshop.coffeeApp.payload.request.OrderRequest;
import com.coffeeshop.coffeeApp.payload.response.OrderItemResponse;
import com.coffeeshop.coffeeApp.payload.response.OrderResponse;
import com.coffeeshop.coffeeApp.repositories.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("menu")
@CrossOrigin(origins = "*")
public class MenuController {

    private final AddInRepository addInRepository;
    private final MenuRepository menuRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderMenuRepository orderMenuRepository;


    MenuController(AddInRepository addInRepository,
                   MenuRepository menuRepository,
                   OrderRepository orderRepository,
                   UserRepository userRepository,
                   OrderMenuRepository orderMenuRepository) {
        this.addInRepository = addInRepository;
        this.menuRepository = menuRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderMenuRepository = orderMenuRepository;
    }

    @GetMapping("/pages")
    public ResponseEntity<?> getMenuPages(@RequestParam(defaultValue = "") String menuItem,
                                          @RequestParam(defaultValue = "1") int currentPage,
                                          @RequestParam(defaultValue = "5") int perPage){
        Pageable pageable = PageRequest.of(currentPage -1,perPage);
        Page<Menu> menuItems = menuRepository.filterMenuPages(pageable,menuItem.toLowerCase());
        Map<String,Object> response = new HashMap<>();
        response.put("totalElements", menuItems.getTotalElements());
        response.put("totalPages", menuItems.getTotalPages());
        response.put("menuItems", menuItems.getContent());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/fetch")
    public List<OrderResponse> getAllOrders(){
        List<Order> orders = orderRepository.findAll();
        List<OrderResponse> orderResponseList = new ArrayList<>();

        for (Order order: orders){
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setCoffeeUserName(order.getCoffeeUser().getFullName());
            orderResponse.setDate(order.getOrderDate());

            for (OrderMenu orderMenu: order.getOrderMenu()){
                OrderItemResponse itemResponse = new OrderItemResponse();
                itemResponse.setItem(orderMenu.getMenu().getItem());
                itemResponse.setFlavor(orderMenu.getMenu().getFlavor());
                itemResponse.setAddIns(orderMenu.getMenu().getAddIns());
                itemResponse.setQuantity(orderMenu.getQuantity());
                orderResponse.getItems().add(itemResponse);
            }
            orderResponseList.add(orderResponse);
        }
        return orderResponseList;
    }

    @PostMapping("/save/addIn")
    public ResponseEntity<?> saveAddIn(String name){
        return ResponseEntity.ok("Saved new AddIn - " + addInRepository.save(new AddIn(name)).getName());
    }

    @PostMapping("/save/menu")
    public ResponseEntity<?> saveMenu(@RequestBody MenuRequest menuRequest){
        Set<AddIn> addIns = new HashSet<>();
        for(AddIn addIn: menuRequest.getAddInName())
        {
            addIns.add(addInRepository.findAddInByName(addIn.getName()));
        }
        Menu menu = menuRepository.save(new Menu(menuRequest.getItemName(), menuRequest.getFlavor(), addIns));
        String message = "Saved new Menu item - " + menu.getItem() + " with flavor " + menu.getFlavor() +" and add-ins: ";
        Boolean printComma = false;
            for(AddIn addin: menu.getAddIns())
            {
                if(printComma)
                    message+=", ";
                message += addin.toString();
                printComma=true;
            }
        return ResponseEntity.ok(message);
    }

    @PostMapping("/place/order")
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequest orderRequest) {

        CoffeeUser coffeeUser = userRepository.findCoffeeUserByUsername(orderRequest.getUsername());
        if(coffeeUser != null) {
            Order order = orderRepository.save(new Order(coffeeUser, new Timestamp(System.currentTimeMillis())));

            for (OrderItemRequest element : orderRequest.getOrders()) {
                Menu menu = menuRepository.findMenuByItem(element.getMenuItem());
                orderMenuRepository.save(new OrderMenu(
                        new OrderMenuKey(order.getId(), menu.getId()),
                        order,
                        menu,
                        element.getQuantity()
                ));
            }

            return ResponseEntity.ok("New order placed at: " + order.getOrderDate());
        }
        else
            return ResponseEntity.badRequest().body("User not found!");
    }

}

