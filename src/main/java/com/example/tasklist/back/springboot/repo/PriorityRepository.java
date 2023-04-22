package com.example.tasklist.back.springboot.repo;

import com.example.tasklist.back.springboot.entity.CategoryEntity;
import com.example.tasklist.back.springboot.entity.PriorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriorityRepository extends JpaRepository<PriorityEntity, Long> {
    @Query("SELECT c from PriorityEntity c " +
            "where (:title is null  or :title='' " +
            "or lower(c.title) like lower(concat('%', :title, '%') ) )" +
            "order by c.title asc")
    List<PriorityEntity> findByTitle(@Param("title") String title);
    List<PriorityEntity> findAllByOrderByIdAsc();
}
