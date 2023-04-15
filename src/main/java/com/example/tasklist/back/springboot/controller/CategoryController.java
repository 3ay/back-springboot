package com.example.tasklist.back.springboot.controller;

import com.example.tasklist.back.springboot.entity.CategoryEntity;
import com.example.tasklist.back.springboot.repo.CategoryRepository;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity <CategoryEntity> addCategory (@RequestBody CategoryEntity category)
    {
        if (category.getId() != null && category.getId() != 0)
        {
            return new ResponseEntity("redundant param: id MUST be without value", HttpStatus.NOT_ACCEPTABLE);
        }
        if (category.getTitle() == null || category.getTitle().trim().length() == 0)
        {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(categoryRepository.save(category));
    }
}
