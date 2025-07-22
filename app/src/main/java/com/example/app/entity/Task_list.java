package com.example.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "task_items")
public class Task_list {
    @Id
    private Long id;
    private String expiration_date;
    private String task_item;
    private Short is_completed;
    private Short is_deleted;
    private String create_date_time;
    private String update_date_time;
}
