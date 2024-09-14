package todoList.todoList.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import todoList.todoList.entity.tasks;
import todoList.todoList.service.TaskService;

import java.util.List;

@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    public String getAllTasks(Model model) {
        List<tasks> tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);
        return "tasks"; // имя шаблона для отображения
    }

    @PostMapping("/tasks")
    public String addTask(@RequestParam String description) {
        tasks task = new tasks();
        task.setDescription(description);
        task.setCompleted(false);
        taskService.save(task);
        return "redirect:/tasks";
    }


    @PostMapping("/tasks/deleteLast")
    public String deleteLastTask() {
        taskService.deleteLastTask();
        return "redirect:/tasks";
    }
}