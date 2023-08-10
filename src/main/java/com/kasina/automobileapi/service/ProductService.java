package com.kasina.automobileapi.service;

import com.kasina.automobileapi.model.Category;
import com.kasina.automobileapi.model.ImageModel;
import com.kasina.automobileapi.model.Product;
import com.kasina.automobileapi.dto.ProductDto;
import com.kasina.automobileapi.model.User;
import com.kasina.automobileapi.repository.ImageModelRepository;
import com.kasina.automobileapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final ImageModelRepository imageModelRepository;
    @Autowired
    private final UserService userService;
    @Autowired
    private final CategoryService categoryService;
    @Value("${upload.path}")
    private String uploadPath;


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

    // add multiple products at once

    /*public List<Product> addMultipleProducts(List<ProductDto> productDtos, MultipartFile[] imageFiles) throws IOException {
        User currentUser = userService.getCurrentUser();
        List<Product> addedProducts = new ArrayList<>();
        List<ImageModel> images = uploadImage(imageFiles);
        for (ProductDto productDto : productDtos) {
            Set<Category> categories = new HashSet<>();
            Product product = new Product();
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setShortDescription(productDto.getShortDescription());
            product.setPrice(productDto.getPrice());
            product.setUser(currentUser);
           // product.setProductImages(images);

            for (String categoryName : productDto.getCategories()) {
                Category category =  categoryService.getDefaultCategory(categoryName);
                categories.add(category);
            }
            product.setCategories(categories);
            addedProducts.add(product);
        }

        return productRepository.saveAll(addedProducts);
    }*/


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

    public List<ImageModel> uploadImage(MultipartFile[] imageFiles) throws IOException {
        
        Set<ImageModel> newImages = new HashSet<>();
        for(MultipartFile imageFile: imageFiles){
            ImageModel file = new ImageModel(
                    imageFile.getOriginalFilename(),
                    imageFile.getContentType(),
                    imageFile.getBytes()
            );
            newImages.add(file);
        }
        return  imageModelRepository.saveAll(newImages);
    }

    public ImageModel saveImage(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        ImageModel image = new ImageModel(fileName, file.getContentType(), file.getBytes());
        return imageModelRepository.save(image);
    }

    public String storeImage(MultipartFile file) {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadPath + fileName);
        try {
            Files.write(filePath, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to store image: " + e.getMessage());
        }
        return fileName;
    }
}
