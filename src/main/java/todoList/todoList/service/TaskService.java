package todoList.todoList.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todoList.todoList.entity.tasks;
import todoList.todoList.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<tasks> findAll() {
        return taskRepository.findAll();
    }

    public tasks save(tasks task) {
        return taskRepository.save(task);
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }


    public void deleteLastTask() {
        List<tasks> tasks = taskRepository.findAll();
        if (!tasks.isEmpty()) {
            Long lastTaskId = tasks.get(tasks.size() - 1).getId();
            deleteById(lastTaskId);
        }
    }


    public Optional<tasks> findById(Long id) {
        return taskRepository.findById(id);
    }
}