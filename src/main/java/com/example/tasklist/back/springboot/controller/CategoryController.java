package com.example.tasklist.back.springboot.controller;

import com.example.tasklist.back.springboot.entity.CategoryEntity;
import com.example.tasklist.back.springboot.repo.CategoryRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryRepository categoryRepository;
    public CategoryController(CategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/getCategories")
    public List<CategoryEntity> test()
    {
        return categoryRepository.findAll();
    }
    @PostMapping("/addCategory")
    public CategoryEntity addCategory (@RequestBody CategoryEntity category)
    {
        return categoryRepository.save(category);
    }
}
