package com.example.tasklist.back.springboot.repo;

import com.example.tasklist.back.springboot.entity.CategoryEntity;
import com.example.tasklist.back.springboot.entity.PriorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriorityRepository extends JpaRepository<PriorityEntity, Long> {
    List<PriorityEntity> findAllByOrderByIdAsc();
}
