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
@Table(name = "priority", schema = "base")
public class PriorityEntity {
    private Long id;
    private String title;
    private String color;

    // указываем, что поле заполняется в БД
    // нужно, когда добавляем новый объект и он возвращается уже с новым id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Basic
    @NotEmpty(message = "title must not be empty")
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    @Basic
    @NotEmpty(message = "color must not be empty")
    @Column(name = "color")
    public String getColor() {
        return color;
    }

    // обратная ссылка на коллекцию Task не нужна
}
