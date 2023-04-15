package com.example.tasklist.back.springboot.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@NoArgsConstructor
@Setter
@EqualsAndHashCode
@Table(name = "category", schema = "base")
public class CategoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @NotEmpty(message = "title must not be empty")
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "completed_count")
    private Long completedCount;
    @Basic
    @Column(name = "uncompleted_count")
    private Long uncompletedCount;
    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public Long getCompletedCount() {
        return completedCount;
    }
    public Long getUncompletedCount() {
        return uncompletedCount;
    }


}
