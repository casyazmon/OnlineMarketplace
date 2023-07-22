package com.kasina.automobileapi.controller;

import com.kasina.automobileapi.dto.OrderRequest;
import com.kasina.automobileapi.dto.ProductDto;
import com.kasina.automobileapi.model.Order;
import com.kasina.automobileapi.model.Product;
import com.kasina.automobileapi.model.User;
import com.kasina.automobileapi.service.OrderService;
import com.kasina.automobileapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;


    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest){
        Order order = orderService.createOrder(orderRequest);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUser(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        List<Order> orders = orderService.getOrdersByUser(user);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
        User currentUser = userService.getCurrentUser();
        Order order = orderService.getOrderByIdAndUser(orderId, currentUser);
        return ResponseEntity.ok(order);
    }
}
