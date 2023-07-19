package com.kasina.automobileapi.service;

import com.kasina.automobileapi.model.Category;
import com.kasina.automobileapi.model.Product;
import com.kasina.automobileapi.model.dto.ProductDto;
import com.kasina.automobileapi.model.dto.UrlErrorResponseDto;
import com.kasina.automobileapi.model.user.User;
import com.kasina.automobileapi.repository.CategoryRepository;
import com.kasina.automobileapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final UserService userService;
    @Autowired
    private final CategoryRepository categoryRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found  with: " + id));
    }

    public ResponseEntity<?> addProduct(ProductDto productDto) {
        User currentUser = userService.getCurrentUser();
        Set<Category> categories = new HashSet<>();

        for (String categoryName : productDto.getCategories()) {
            Category category = categoryRepository.findCategoryByName(categoryName)
                    .orElseGet(() -> {
                        Category newCategory = new Category();
                        newCategory.setName(categoryName);
                        return categoryRepository.save(newCategory);
                    });
            categories.add(category);
        }
        var pdt = Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .shortDescription(productDto.getShortDescription())
                .price(productDto.getPrice())
                .image(productDto.getImage())
                .user(currentUser)
                .categories(categories)
                .build();

        return ResponseEntity.ok(productRepository.save(pdt));
    }


  /*  public Optional<List<Product>> getProductByCategory(Category category){
       return productRepository.findProductByCategory(category);
    }*/

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Optional<List<Product>> getUserProducts() {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null){
            UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
            urlErrorResponseDto.setStatus("User not found");
            urlErrorResponseDto.setError("422");
             return null;
        }
        return productRepository.findByUser(currentUser);
    }
}
