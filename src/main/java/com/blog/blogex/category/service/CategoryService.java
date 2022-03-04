package com.blog.blogex.category.service;

import com.blog.blogex.category.dto.CategoryDto;
import com.blog.blogex.category.entity.Category;
import com.blog.blogex.category.entity.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
//
//    @Transactional
//    public void createCategory(CategoryDto.SaveRequest request){
//        Boolean existByName = categoryRepository.existsByCategoryName(request.getCategoryName());
//        if (existByName){
//            throw new IllegalArgumentException("카테고리 중복");
//        }
//
//        if (request.getParentCategory() == null){
//            CategoryDto.SaveRequest rootCategory = CategoryDto.SaveRequest.builder()
//                    .categoryName(request.getCategoryName())
//                    .parentCategory("대분류")
//                    .build();
//
//            categoryRepository.save(rootCategory.toEntity());
//        }else{
//            String parentCategory1 = request.getParentCategory();
//            Category parentCategory = categoryRepository.findByCategoryName(parentCategory1)
//                    .orElseThrow();
//            Category category = Category.builder()
//                    .categoryName(request.getCategoryName())
//                    .parentCategory(parentCategory)
//                    .build();
//            parentCategory.getChildCategory().add(request.toEntity());
//            categoryRepository.save(category);
//        }
//    }

    @Transactional(readOnly = true)
    public List<Category> categories(){
        List<Category> categories = categoryRepository.findAll();

        return categories;
    }

    @Transactional(readOnly = true)
    public CategoryDto.CategoryResponse getCategoryById(Long id){
        CategoryDto.CategoryResponse response = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("찾는 카테고리가 존재하지 않음"))
                .toCategoryResponse();
        return response;
    }

    @Transactional
    public void updateCategory(Long id, CategoryDto.SaveRequest request){
        Category category = categoryRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        category.update(request);
    }

    @Transactional
    public void deleteCategory(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        categoryRepository.deleteById(id);
    }
}
