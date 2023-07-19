package com.kasina.automobileapi.service;

import com.kasina.automobileapi.model.Category;
import com.kasina.automobileapi.model.dto.UrlErrorResponseDto;
import com.kasina.automobileapi.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public ResponseEntity<?> createCategory(Category category){
       if (categoryRepository.findCategoryByName(category.getName()).isPresent()){
           UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
           urlErrorResponseDto.setStatus("Category already exist");
           urlErrorResponseDto.setError("200");
           return new ResponseEntity<>(urlErrorResponseDto, HttpStatus.FORBIDDEN);
       }

       return ResponseEntity.ok(categoryRepository.save(category));
    }

    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryRepository.findAll());
    }

    public ResponseEntity<?> getCategoryById(Long id){
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()){
            UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
            urlErrorResponseDto.setStatus("Category already exist");
            urlErrorResponseDto.setError("200");
            return new ResponseEntity<>(urlErrorResponseDto, HttpStatus.FORBIDDEN);
        }
        return ResponseEntity.ok(category);
    }

    public String deleteCategory(Long catId) {
        if (categoryRepository.findById(catId).isEmpty()){
            return "Unable to delete. Category not found";
        }
        categoryRepository.deleteById(catId);
        return "Category Deleted Successfully";

    }
}
