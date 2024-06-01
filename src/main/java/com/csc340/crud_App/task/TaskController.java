package com.csc340.crud_App.task;

import com.csc340.crud_App.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/{goalId}")
    public String getAllTasks(@PathVariable int goalId, Model model) {
        model.addAttribute("tasks", taskService.getAllTasks(goalId));
        model.addAttribute("goalId", goalId);
        return "tasks/list";
    }

    @GetMapping("/{goalId}/new")
    public String createTaskForm(@PathVariable int goalId, Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("goalId", goalId);
        return "tasks/form";
    }

    @PostMapping("/{goalId}/new")
    public String saveTask(@PathVariable int goalId, @ModelAttribute Task task) {
        task.setGoalId(goalId);
        taskService.saveTask(task);
        return "redirect:/tasks/" + goalId;
    }

    @GetMapping("/{taskId}/edit")
    public String editTaskForm(@PathVariable int taskId, Model model) {
        Optional<Task> task = taskService.getTaskById(taskId);
        if (task.isPresent()) {
            model.addAttribute("task", task.get());
            return "tasks/form";
        } else {
            throw new IllegalArgumentException("Invalid task id");
        }
    }

    @PostMapping("/{taskId}/edit")
    public String updateTask(@PathVariable int taskId, @ModelAttribute Task task) {
        task.setTaskId(taskId);
        taskService.saveTask(task);
        return "redirect:/tasks/" + task.getGoalId();
    }

    @PostMapping("/{taskId}/delete")
    public String deleteTask(@PathVariable int taskId) {
        Task task = taskService.getTaskById(taskId).orElseThrow(() -> new IllegalArgumentException("Invalid task id"));
        taskService.deleteTask(taskId);
        return "redirect:/tasks/" + task.getGoalId();
    }
}
