package com.kasina.automobileapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private List<OrderItemRequest> items;
    private BigDecimal price;
    private String status;
    private String shippingAddress;
    // Add more fields for payment details, etc.
}
