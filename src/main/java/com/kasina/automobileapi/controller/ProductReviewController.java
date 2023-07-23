package com.kasina.automobileapi.controller;

import com.kasina.automobileapi.model.Order;
import com.kasina.automobileapi.model.ProductReview;
import com.kasina.automobileapi.model.User;
import com.kasina.automobileapi.service.ProductReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productReview")
@RequiredArgsConstructor
public class ProductReviewController {
    private final ProductReviewService productReviewService;

    @PostMapping("/{id}")
    public ResponseEntity<ProductReview> saveReview(@RequestBody ProductReview productReview, @PathVariable Long id){
        ProductReview newReview = productReviewService.createReview(productReview, id);
        return ResponseEntity.ok(newReview);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductReview>> getProductReviews(@PathVariable Long productId) {
        List<ProductReview> productReviews = productReviewService.getProductReviews(productId);
        return ResponseEntity.ok(productReviews);
    }

    @DeleteMapping("/reviewId")
    public String deleteProductReview(@PathVariable Long reviewId){
            return productReviewService.deleteProductReview(reviewId);
    }
}
