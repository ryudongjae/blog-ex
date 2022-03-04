package com.blog.blogex.category.controller;

import com.blog.blogex.category.dto.CategoryDto;
import com.blog.blogex.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.blog.blogex.category.ResponseConstants.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryApiController {

    private final CategoryService categoryService;

//    @PostMapping
//    public ResponseEntity<Void> saveCategory(@Valid @RequestBody CategoryDto.SaveRequest request){
//        categoryService.createCategory(request);
//        return OK;
//    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto.CategoryResponse> getCategoryById(@PathVariable(name = "id") Long categoryId){
        CategoryDto.CategoryResponse categoryById = categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok().body(categoryById);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable(name = "id") Long id,
                                               @Valid@RequestBody CategoryDto.SaveRequest request){
        categoryService.updateCategory(id, request);

        return OK;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable(name = "id")Long id){
        categoryService.deleteCategory(id);
        return OK;
    }
}

