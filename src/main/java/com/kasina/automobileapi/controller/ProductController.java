package com.kasina.automobileapi.controller;

import com.kasina.automobileapi.model.Product;
import com.kasina.automobileapi.dto.ProductDto;
import com.kasina.automobileapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getProductByUser(@PathVariable Long id) {
        Optional<List<Product>> product = productService.getUserProducts(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getProductByCategory(@PathVariable Long id) {
        Optional<List<Product>> product = productService.findProductByCategory(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

  /*  @PostMapping(value = "/add-products", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<Product>> addMultipleProducts(@RequestBody List<ProductDto> productDtos,
                                                             @RequestPart("imageFile")MultipartFile[] imageFile) {

        try{
            List<Product> addedProducts = productService.addMultipleProducts(productDtos, imageFile);
            return new ResponseEntity<>(addedProducts, HttpStatus.CREATED);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }*/

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody ProductDto productDto){
        Product product = productService.createProduct(productDto);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDto productDto, @PathVariable Long productId){
        Product product = productService.updateProduct(productDto,productId);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok("Product deleted successfully");
    }


}
