package com.example.tasklist.back.springboot.controller;

import com.example.tasklist.back.springboot.entity.PriorityEntity;
import com.example.tasklist.back.springboot.repo.PriorityRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping ("/priority") // базовый адрес
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
    public PriorityEntity addCategory(@RequestBody PriorityEntity category)
    {
        return priorityRepository.save(category);
    }


}