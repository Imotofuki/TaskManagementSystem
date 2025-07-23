package com.example.app.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "task_items")
public class Task_list {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate expirationDate;
    private String taskItem;
    private Short isCompleted;
    private Short isDeleted;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
}
