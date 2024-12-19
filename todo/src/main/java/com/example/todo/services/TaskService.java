package com.example.todo.services;

import com.example.todo.models.Task;
import com.example.todo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {


    private final TaskRepository taskRepository;

    private TaskService(TaskRepository taskRepository){
        this.taskRepository=taskRepository;
    }
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
        

    }

    public void createTask(String title) {
        Task task = new Task();
        task.setTitle(title);
        task.setCompleted(false);
        taskRepository.save(task);

    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);

    }

    public void toggleTask(Long id) {
        Task task= taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task id"));
                task.setCompleted(!task.isCompleted());
                taskRepository.save(task);
    }

    public Task getTaskById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.orElse(null);
    }

    public Task saveOrUpdateTask(Task task) {
        // Save new task or update existing task
        return taskRepository.save(task);
    }






}
