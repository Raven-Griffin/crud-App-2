package com.csc340.crud_App.task;

import com.csc340.crud_App.task.Task;
import com.csc340.crud_App.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks(int goalId) {
        return taskRepository.findByGoalId(goalId);
    }

    public Optional<Task> getTaskById(int taskId) {
        return taskRepository.findById(taskId);
    }

    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    public void deleteTask(int taskId) {
        taskRepository.deleteById(taskId);
    }
}

