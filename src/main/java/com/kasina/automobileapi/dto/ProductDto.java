package com.kasina.automobileapi.dto;

import com.kasina.automobileapi.model.ImageModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String name;
    private String description;
    private String shortDescription;
    private BigDecimal price;
    private List<String> categories;
    private String image;
}
