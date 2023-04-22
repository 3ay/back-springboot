package com.example.tasklist.back.springboot.controller;

import com.example.tasklist.back.springboot.entity.StatEntity;
import com.example.tasklist.back.springboot.repo.StatRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/stat")
public class StatController {
    private StatRepository statRepository;

    public StatController(StatRepository statRepository) {
        this.statRepository = statRepository;
    }
private final Long defaultId = 1l;
    @GetMapping("/all")
    public ResponseEntity<StatEntity> findById()
    {
        return ResponseEntity.ok(statRepository.findById(defaultId).get());
    }
}
