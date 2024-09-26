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
import java.util.Optional;
import java.util.Random;

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
        task.setColor(generateRandomColor());
        taskService.save(task);
        return "redirect:/tasks";
    }


    @PostMapping("/tasks/deleteLast")
    public String deleteLastTask() {
        taskService.deleteLastTask();
        return "redirect:/tasks";
    }

    // Новый метод для отображения формы редактирования
    @GetMapping("/tasks/edit")
    public String editTask(@RequestParam Long id, Model model) {
        Optional<tasks> task = taskService.findById(id);
        if (task.isPresent()) {
            model.addAttribute("task", task.get());
            return "editTask"; // имя шаблона для редактирования
        }
        return "redirect:/tasks"; // если задача не найдена, перенаправляем на список задач
    }

    // Новый метод для обработки обновления задачи
    @PostMapping("/tasks/update")
    public String updateTask(@RequestParam Long id, @RequestParam String description) {
        Optional<tasks> optionalTask = taskService.findById(id);
        if (optionalTask.isPresent()) {
            tasks task = optionalTask.get();
            task.setDescription(description);
            taskService.save(task);
        }
        return "redirect:/tasks";
    }

    private String generateRandomColor() {
        String[] colors = {"red", "blue", "green"};
        int randomIndex = new Random().nextInt(colors.length);
        return colors[randomIndex];
    }
}