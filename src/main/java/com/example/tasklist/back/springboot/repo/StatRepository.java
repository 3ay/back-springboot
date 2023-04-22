package com.example.tasklist.back.springboot.repo;

import com.example.tasklist.back.springboot.entity.StatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatRepository extends JpaRepository<StatEntity, Long> {
    List<StatEntity> findAllByOrderByIdAsc();
}
