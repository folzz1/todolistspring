package todoList.todoList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todoList.todoList.entity.tasks;

public interface TaskRepository extends JpaRepository<tasks, Long> {

}