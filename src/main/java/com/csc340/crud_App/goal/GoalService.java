package com.csc340.crud_App.goal;

import com.csc340.crud_App.goal.Goal;
import com.csc340.crud_App.goal.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoalService {

    @Autowired
    private GoalRepository goalRepository;

    public List<Goal> getAllGoals() {
        return goalRepository.findAll();
    }

    public Optional<Goal> getGoalById(int id) {
        return goalRepository.findById(id);
    }

    public void saveGoal(Goal goal) {
        goalRepository.save(goal);
    }

    public void deleteGoal(int id) {
        goalRepository.deleteById(id);
    }
}
