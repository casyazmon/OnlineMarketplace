package com.kasina.automobileapi.service;

import com.kasina.automobileapi.model.Category;
import com.kasina.automobileapi.model.Product;
import com.kasina.automobileapi.dto.ProductDto;
import com.kasina.automobileapi.dto.UrlErrorResponseDto;
import com.kasina.automobileapi.model.User;
import com.kasina.automobileapi.repository.ProductRepository;
import com.kasina.automobileapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final UserService userService;
    @Autowired
    private final CategoryService categoryService;

    public Product createProduct(ProductDto productDto) {

        Set<Category> categories = new HashSet<>();
        User currentUser = userService.getCurrentUser();
        Product product = new Product();
         product = Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .shortDescription(productDto.getShortDescription())
                .price(productDto.getPrice())
                .image(productDto.getImage())
                .user(currentUser)
                .build();

        // Then, associate the product with the specified categories
        for (String categoryName : productDto.getCategories()) {
           Category category =  categoryService.getDefaultCategory(categoryName);
           categories.add(category);
        }
        product.setCategories(categories);

        return productRepository.save(product);
    }

    public  Product updateProduct(ProductDto productDto, Long id){
        Set<Category> categories = new HashSet<>();
        User currentUser = userService.getCurrentUser();
        Product product = getProductById(id);
        product = Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .shortDescription(productDto.getShortDescription())
                .price(productDto.getPrice())
                .image(productDto.getImage())
                .build();

        // Then, associate the product with the specified categories
        for (String categoryName : productDto.getCategories()) {

            Category category =  categoryService.getDefaultCategory(categoryName);
            categories.add(category);
        }
        product.setCategories(categories);

        return productRepository.save(product);

    }

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get a product by ID
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    // Delete a product by ID
    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
    }


    public Optional<List<Product>> getUserProducts(Long userId) {
        User currentUser = userService.getUserById(userId);
        return productRepository.findProductByUser(currentUser);
    }

    public Optional<List<Product>> findProductByCategory(Long catId) {
        Category category = categoryService.getCategoryById(catId);
        return productRepository.findByCategories(category);
    }
}
