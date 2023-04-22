package com.example.tasklist.back.springboot.repo;

import com.example.tasklist.back.springboot.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    // получить все значения, сортировка по названию
    List<CategoryEntity> findAllByOrderByIdAsc();
}
