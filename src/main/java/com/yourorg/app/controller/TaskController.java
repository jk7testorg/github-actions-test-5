package com.yourorg.app.controller;

import com.yourorg.app.model.Task;
import com.yourorg.app.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.yourorg.dsspsharedlib.DateUtils;
@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String listTasks(Model model) {
        return showList(model);
    }

    @PostMapping("/tasks")
    public String addTask(@Valid @ModelAttribute("newTask") Task newTask,
                           BindingResult result,
                           Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tasks", taskService.findAll());
            return "index";
        }
        taskService.add(newTask);
        return "redirect:/";
    }

    @PostMapping("/tasks/{id}/toggle")
    public String toggleTask(@PathVariable Long id) {
        taskService.toggleDone(id);
        return "redirect:/";
    }

    @PostMapping("/tasks/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        taskService.delete(id);
        return "redirect:/";
    }

    private String showList(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        model.addAttribute("newTask", new Task());
        model.addAttribute("lastRefreshed", DateUtils.now());
        return "index";
    }

}
