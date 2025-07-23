package com.example.app.controller;


import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.domain.Sort;
// import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.entity.Task_list;
import com.example.app.repository.TaskRepository;
import org.springframework.ui.Model;

@Controller
public class HelloController {

    private final TaskRepository taskRepository;
    public HelloController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    // SELECT * FROM task_items ORDER BY expiration_date ASC;
    @GetMapping("/")
    public String hello(Model model) {
        model.addAttribute("tasks", taskRepository.findAll(Sort.by(
            Sort.Order.asc("expirationDate"),
             Sort.Order.desc("isCompleted"))));
        return "index";
    }
    @PostMapping("/add")
    public String addTask(@RequestParam String date, @RequestParam String task_item) {
        Task_list task = new Task_list();
        if(date == null || date.isEmpty()) {
            date = LocalDate.now().toString(); // Default to today's date if no date is provided
        }
        task.setExpirationDate(LocalDate.parse(date));
        task.setTaskItem(task_item);
        task.setIsCompleted((short) 0); // Default to not completed
        task.setIsDeleted((short) 0); // Default to not deleted
        task.setCreateDateTime(LocalDateTime.now());
        task.setUpdateDateTime(LocalDateTime.now());
        
        taskRepository.save(task);
        return "redirect:/"; // Redirect to the index page after adding a task
    }
    @PostMapping("/update")
    public String updateTask(@RequestParam String process, @RequestParam Long id) {
        Task_list task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        Boolean flag = false;
        switch (process) {
            case "not":
                task.setIsCompleted((short) 0);
                task.setIsDeleted((short) 0);
                break;
            case "complete":
                task.setIsCompleted((short) 1);
                break;
            case "delete":
                task.setIsDeleted((short) 1);
                flag = true; // Flag to indicate deletion
                break;
            default:
                throw new IllegalArgumentException("Invalid process: " + process);
        }
        task.setUpdateDateTime(LocalDateTime.now());
        taskRepository.save(task);
        if (flag) {
            taskRepository.delete(task); // Delete the task if flagged
        }
        return "redirect:/"; // Redirect to the index page after updating a task
    }
    
}
