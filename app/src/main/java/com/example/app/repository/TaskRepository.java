package com.example.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.app.entity.Task_list;

public interface TaskRepository extends JpaRepository<Task_list, Long> {
}
