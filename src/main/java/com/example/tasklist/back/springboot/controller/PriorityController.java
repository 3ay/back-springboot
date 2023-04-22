package com.example.tasklist.back.springboot.controller;

import com.example.tasklist.back.springboot.entity.CategoryEntity;
import com.example.tasklist.back.springboot.entity.PriorityEntity;
import com.example.tasklist.back.springboot.repo.PriorityRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/priority") // базовый адрес
public class PriorityController {

    // доступ к данным из БД
    private PriorityRepository priorityRepository;

    // автоматическое внедрение экземпляра класса через конструктор
    // не используем @Autowired ля переменной класса, т.к. "Field injection is not recommended "
    public PriorityController(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }


    // для тестирования адрес: http://localhost:8080/priority/test

    @GetMapping("/getPriorities")
    public List<PriorityEntity> test() {


        return priorityRepository.findAll(); // JSON формат будет использоваться автоматически

    }

    @PostMapping("/addPriority")
    public ResponseEntity<PriorityEntity> addPriority(@Valid @RequestBody PriorityEntity priority) {
        if (priority.getId() != null && priority.getId() != 0) {
            return new ResponseEntity("redundant param: id MUST be without value", HttpStatus.NOT_ACCEPTABLE);
        }
        if (priority.getTitle() == null || priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(priorityRepository.save(priority));
    }

    @PutMapping("/updatePriority")
    public ResponseEntity<PriorityEntity> updateCategory(@Valid @RequestBody PriorityEntity priority) {
        if (priority.getId() != null && priority.getId() != 0) {
            if (priorityRepository.existsById(priority.getId()) && priorityRepository.existsById(priority.getId())) {
                return ResponseEntity.ok(priorityRepository.save(priority));
            } else
                return new ResponseEntity("redundant param: id doesn't exist", HttpStatus.NOT_ACCEPTABLE);
        } else {
            return new ResponseEntity("redundant param: id MUST be determined", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PriorityEntity> findById(@Valid @PathVariable Long id) {
        PriorityEntity priority;
        try {
           priority = priorityRepository.findById(id).get();
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(priority);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        try {
            priorityRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            exception.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("/all")
    public List<PriorityEntity> findAll()
    {
        return priorityRepository.findAllByOrderByIdAsc();
    }

}