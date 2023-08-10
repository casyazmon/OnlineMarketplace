package com.kasina.automobileapi.service;

import com.kasina.automobileapi.model.Category;
import com.kasina.automobileapi.model.User;
import com.kasina.automobileapi.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    // Create a new Category
    public Category createCategory(Category category) {
        Optional<Category> defaultCategoryOpt = categoryRepository.findByName(category.getName());
        return defaultCategoryOpt.orElseGet(() -> categoryRepository.save(category));
    }

    // Get all Categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Get a Category by ID
    public Category getCategoryById(Long userId) {
        return categoryRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("Category not found with ID: " + userId));
    }

    public Optional<Category> getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    // Update a Category
    public Category updateCategory(Category updatedCategory) {
        return categoryRepository.save(updatedCategory);
    }

    // Delete a Category by ID
    public void deleteCategoryById(Long CategoryId) {
        categoryRepository.deleteById(CategoryId);
    }

    // Get or create a default category
    public Category getDefaultCategory(String name) {
        Optional<Category> defaultCategoryOpt = categoryRepository.findByName(name);
        if (defaultCategoryOpt.isPresent()) {
            return defaultCategoryOpt.get();
        } else {
            Category defaultCategory = new Category();
            defaultCategory.setName(name);
            return categoryRepository.save(defaultCategory);
        }
    }
}
