package com.kasina.automobileapi.model.dto;

import com.kasina.automobileapi.model.Category;
import com.kasina.automobileapi.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String name;
    private String description;
    private String shortDescription;
    private BigDecimal price;
    private String image;
    private User user;
    private Set<String> categories;
}
