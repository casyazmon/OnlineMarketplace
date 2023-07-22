package com.kasina.automobileapi.repository;

import com.kasina.automobileapi.model.Product;
import com.kasina.automobileapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<List<Product>> findByUser(User user);
//    List<Product> findProductByCategory(Long catId);


}
