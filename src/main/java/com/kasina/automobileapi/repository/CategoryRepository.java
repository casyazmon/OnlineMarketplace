package com.kasina.automobileapi.repository;

import com.kasina.automobileapi.model.Category;
import com.kasina.automobileapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository  extends JpaRepository<Category, Long> {
    /*List<Category> findCategoryByProduct(Long productId);*/

    Optional<Category> findByName(String name);
}
