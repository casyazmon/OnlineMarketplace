package com.kasina.automobileapi.controller;

import com.kasina.automobileapi.model.Category;
import com.kasina.automobileapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{catId}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long catId){
        return categoryService.getCategoryById(catId);
    }

    @DeleteMapping("/{catId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long catId) {
        return ResponseEntity.ok( categoryService.deleteCategory(catId));
    }
}
