package com.kasina.automobileapi.service;

import com.kasina.automobileapi.dto.OrderItemRequest;
import com.kasina.automobileapi.dto.OrderRequest;
import com.kasina.automobileapi.model.Order;
import com.kasina.automobileapi.model.OrderItem;
import com.kasina.automobileapi.model.Product;
import com.kasina.automobileapi.model.User;
import com.kasina.automobileapi.repository.OrderRepository;
import com.kasina.automobileapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserService userService;


    public Order createOrder(OrderRequest orderRequest) {
        User currentUser = userService.getCurrentUser();
        BigDecimal grandTotal = BigDecimal.ZERO;

        // Create the order entity
        Order order = new Order();
        order.setUser(currentUser);
        order.setStatus("Pending");
        order.setShippingAddress(orderRequest.getShippingAddress());


        // Create order items and add them to the order
        for (OrderItemRequest itemRequest : orderRequest.getItems()) {
            OrderItem orderItem = new OrderItem();
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));

            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());
            BigDecimal subTotal = product.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity()));
            orderItem.setSubTotal(subTotal);

            // Calculate and set other order item attributes if needed

            order.addOrderItem(orderItem);
            grandTotal = grandTotal.add(subTotal);
        }
        order.setTotalPrice(grandTotal);

        // Save the order in the database
        return orderRepository.save(order);
    }

    public List<Order> getOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }
    public Order getOrderByIdAndUser(Long orderId, User user) {
        return orderRepository.findByIdAndUser(orderId, user)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }


    public Order updateOrder(OrderRequest orderRequest, Long orderId) {
        User currentUser = userService.getCurrentUser();

        BigDecimal grandTotal = BigDecimal.ZERO;

        // Create the order entity
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        order.setStatus(orderRequest.getStatus());
        order.setShippingAddress(orderRequest.getShippingAddress());


        // Create order items and add them to the order
        for (OrderItemRequest itemRequest : orderRequest.getItems()) {
            OrderItem orderItem = new OrderItem();
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));

            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());
            BigDecimal subTotal = product.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity()));
            orderItem.setSubTotal(subTotal);

            // Calculate and set other order item attributes if needed

            order.addOrderItem(orderItem);
            grandTotal = grandTotal.add(subTotal);
        }
        order.setTotalPrice(grandTotal);

        // Save the order in the database
        return orderRepository.save(order);
    }
}
