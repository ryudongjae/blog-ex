package com.blog.blogex.category.entity.repository;

import com.blog.blogex.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByCategoryName(String name);
    Boolean existsByCategoryName(String name);
}
