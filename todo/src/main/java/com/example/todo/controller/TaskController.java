package com.example.todo.controller;

import com.example.todo.models.Task;
import com.example.todo.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String getTasks(Model model, @RequestParam(value = "editId", required = false) Long editId) {
        model.addAttribute("tasks", taskService.getAllTasks());
        if (editId != null) {
            model.addAttribute("task", taskService.getTaskById(editId));
        } else {
            model.addAttribute("task", new Task());
        }
        return "tasks";
    }

    @PostMapping
    public String createTask(@RequestParam String title){

        taskService.createTask(title);
        return "redirect:/";

    }


    @PostMapping("/tasks/save")
    public String saveTask(@ModelAttribute Task task) {
        taskService.saveOrUpdateTask(task);
        return "redirect:/";
    }

    @GetMapping("/tasks/edit/{id}")
    public String editTask(@PathVariable Long id, Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        model.addAttribute("task", taskService.getTaskById(id));
        return "tasks";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id){

        taskService.deleteTask(id);
        return "redirect:/";

    }

    @GetMapping("/toggle/{id}")
    public String toggleTask(@PathVariable Long id){

        taskService.toggleTask(id);
        return "redirect:/";

    }
}
