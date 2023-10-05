package com.example.manageequipment.repository;

import com.example.manageequipment.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
