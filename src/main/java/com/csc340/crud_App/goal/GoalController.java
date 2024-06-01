package com.csc340.crud_App.goal;

import com.csc340.crud_App.goal.Goal;
import com.csc340.crud_App.goal.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/goals")
public class GoalController {

    @Autowired
    private GoalService goalService;

    @GetMapping
    public String getAllGoals(Model model) {
        List<Goal> goals = goalService.getAllGoals();
        model.addAttribute("goals", goals);
        return "goals/list";
    }

    @GetMapping("/{id}")
    public String getGoalById(@PathVariable int id, Model model) {
        Optional<Goal> goal = goalService.getGoalById(id);
        if (goal.isPresent()) {
            model.addAttribute("goal", goal.get());
            return "goals/view";
        } else {
            throw new IllegalArgumentException("Invalid goal id");
        }
    }

    @GetMapping("/new")
    public String createGoalForm(Model model) {
        model.addAttribute("goal", new Goal());
        return "goals/form";
    }

    @PostMapping("/new")
    public String saveGoal(@ModelAttribute Goal goal) {
        goalService.saveGoal(goal);
        return "redirect:/goals";
    }

    @GetMapping("/{id}/edit")
    public String editGoalForm(@PathVariable int id, Model model) {
        Optional<Goal> goal = goalService.getGoalById(id);
        if (goal.isPresent()) {
            model.addAttribute("goal", goal.get());
            return "goals/form";
        } else {
            throw new IllegalArgumentException("Invalid goal id");
        }
    }

    @PostMapping("/{id}/edit")
    public String updateGoal(@PathVariable int id, @ModelAttribute Goal goal) {
        goal.setGoalId(id);
        goalService.saveGoal(goal);
        return "redirect:/goals/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deleteGoal(@PathVariable int id) {
        goalService.deleteGoal(id);
        return "redirect:/goals";
    }
}

