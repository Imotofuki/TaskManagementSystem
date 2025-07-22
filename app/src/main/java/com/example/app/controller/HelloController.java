package com.example.app.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.app.repository.TaskRepository;
import org.springframework.ui.Model;

@Controller
public class HelloController {

    private final TaskRepository taskRepository;
    public HelloController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    @GetMapping("/")
    public String hello(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        return "index"; // This will return a view named "hello"
    }

    
}
