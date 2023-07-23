package com.kasina.automobileapi.service;

import com.kasina.automobileapi.model.Product;
import com.kasina.automobileapi.model.ProductReview;
import com.kasina.automobileapi.model.User;
import com.kasina.automobileapi.repository.ProductReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductReviewService {
    @Autowired
    private final ProductReviewRepository productReviewRepository;
    @Autowired
    private final UserService userService;
    @Autowired
    private final ProductService productService;

    public ProductReview createReview(ProductReview productReview, Long productId){
        User currentUser = userService.getCurrentUser();
        Product product = productService.getProductById(productId);

        var newReview = ProductReview.builder()
                .user(currentUser)
                .product(product)
                .reviewText(productReview.getReviewText())
                .rating(productReview.getRating())
                .build();

        return productReviewRepository.save(newReview) ;
    }
    // Get all reviews for a particular product

    public List<ProductReview> getProductReviews(Long productId) {
        Product product = productService.getProductById(productId);
        return productReviewRepository.findByProduct(product);
    }

    public List<ProductReview> getAllReviews() {
        return productReviewRepository.findAll();
    }

    public ProductReview getReviewById(Long reviewId) {
        return productReviewRepository.findById(reviewId)
                .orElseThrow(() -> new NoSuchElementException("Review not found with ID: " + reviewId));
    }

    // Delete a Category by ID
    public String deleteProductReview(Long reviewId) {
        productReviewRepository.deleteById(reviewId);
        return "Review deleted Successfully";
    }
}
