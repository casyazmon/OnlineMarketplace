package com.kasina.automobileapi.repository;

import com.kasina.automobileapi.model.Category;
import com.kasina.automobileapi.model.Product;
import com.kasina.automobileapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    Optional<List<Product>> findProductByUser(User user);
    Optional<List<Product>> findByCategories(Category category);


}
