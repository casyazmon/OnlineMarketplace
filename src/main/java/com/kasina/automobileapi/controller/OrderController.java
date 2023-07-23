package com.kasina.automobileapi.controller;

import com.kasina.automobileapi.dto.OrderRequest;
import com.kasina.automobileapi.dto.ProductDto;
import com.kasina.automobileapi.model.Order;
import com.kasina.automobileapi.model.Product;
import com.kasina.automobileapi.model.User;
import com.kasina.automobileapi.service.OrderService;
import com.kasina.automobileapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
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

    @PutMapping("/{orderId}")
    public ResponseEntity<?> updateOrder(@RequestBody OrderRequest orderRequest, @PathVariable Long orderId){
        Order order = orderService.updateOrder(orderRequest, orderId);
        return ResponseEntity.ok(order);
    }


    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
        User currentUser = userService.getCurrentUser();
        Order order = orderService.getOrderByIdAndUser(orderId, currentUser);
        return ResponseEntity.ok(order);
    }
}
