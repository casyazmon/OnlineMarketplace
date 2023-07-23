package com.kasina.automobileapi.repository;

import com.kasina.automobileapi.model.Product;
import com.kasina.automobileapi.model.ProductReview;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
    List<ProductReview> findByProduct(Product product);
}
