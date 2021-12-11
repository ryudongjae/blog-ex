package com.blog.blogex.category.entity;

import com.blog.blogex.category.dto.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private Long id;

    private String categoryName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    private List<Category>childCategory =new ArrayList<>();


    @Builder
    public Category(String categoryName, Category parentCategory, List<Category> childCategory) {
        this.categoryName = categoryName;
        this.parentCategory = parentCategory;
        this.childCategory = childCategory;
    }

    public CategoryDto.CategoryResponse toCategoryResponse(){
        return CategoryDto.CategoryResponse.builder()
                .id(this.id)
                .categoryName(this.categoryName)
                .subCategory(
                        this.childCategory.stream().collect(Collectors.toMap(
                                Category::getCategoryName, CategoryDto.CategoryInfo::new
                        ))
                )
                .build();
    }

    public void update(CategoryDto.SaveRequest request){
        this.categoryName = request.getCategoryName();
        this.parentCategory = request.toEntity().getParentCategory();
    }
}
