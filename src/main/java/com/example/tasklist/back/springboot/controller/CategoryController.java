package com.example.tasklist.back.springboot.controller;

import com.example.tasklist.back.springboot.entity.CategoryEntity;
import com.example.tasklist.back.springboot.entity.PriorityEntity;
import com.example.tasklist.back.springboot.repo.CategoryRepository;

import com.example.tasklist.back.springboot.search.CategorySearchValues;
import org.apache.coyote.Response;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/getCategories")
    public List<CategoryEntity> test() {
        return categoryRepository.findAll();
    }

    @PostMapping("/addCategory")
    public ResponseEntity<CategoryEntity> addCategory(@Valid @RequestBody CategoryEntity category) {
        if (category.getId() != null && category.getId() != 0) {
            return new ResponseEntity("redundant param: id MUST be without value", HttpStatus.NOT_ACCEPTABLE);
        }
        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(categoryRepository.save(category));
    }

    @PutMapping("/updateCategory")
    public ResponseEntity<CategoryEntity> updateCategory(@Valid @RequestBody CategoryEntity category) {
        if (category.getId() != null && category.getId() != 0) {
            if (categoryRepository.existsById(category.getId())) {
                return ResponseEntity.ok(categoryRepository.save(category));
            } else
                return new ResponseEntity("redundant param: id doesn't exist", HttpStatus.NOT_ACCEPTABLE);
        } else {
            return new ResponseEntity("redundant param: id MUST be determined", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CategoryEntity> findById(@Valid @PathVariable Long id) {
        CategoryEntity category;
        try {
            category = categoryRepository.findById(id).get();
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        try {
            categoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            exception.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("/all")
    public List<CategoryEntity> findAll()
    {
        return categoryRepository.findAllByOrderByIdAsc();
    }
    @PostMapping("/search")
    //поиск по любым параметрам CategorySearchValues
    public ResponseEntity<List<CategoryEntity>> search(@RequestBody CategorySearchValues values)
    {
        return ResponseEntity.ok(
                categoryRepository.findByTitle(values.getText()));
    }


}
