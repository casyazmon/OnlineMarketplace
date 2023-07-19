package com.kasina.automobileapi.controller;

import com.kasina.automobileapi.model.Category;
import com.kasina.automobileapi.model.Product;
import com.kasina.automobileapi.model.dto.ProductDto;
import com.kasina.automobileapi.model.dto.UrlErrorResponseDto;
import com.kasina.automobileapi.model.user.User;
import com.kasina.automobileapi.repository.UserRepository;
import com.kasina.automobileapi.service.ProductService;
import com.kasina.automobileapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody ProductDto productDto){
        return productService.addProduct(productDto);
    }

    /*@GetMapping("/category/{catId}")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable Long catId) {
        Optional<List<Product>> products = productService.getProductByCategory();
        return new ResponseEntity<Product>(products, HttpStatus.OK);
    }*/



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
