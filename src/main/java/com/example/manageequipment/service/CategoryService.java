package com.example.manageequipment.service;

import com.example.manageequipment.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    Category createCategory(Category category);

    List<Category> getCategory();

    Category updateCategory(Category category);

    void deleteCategory(List<Long> categoryId);
}
