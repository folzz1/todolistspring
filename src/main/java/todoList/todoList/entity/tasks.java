package todoList.todoList.entity;

import jakarta.persistence.Entity; // Измените на jakarta
import jakarta.persistence.GeneratedValue; // Измените на jakarta
import jakarta.persistence.GenerationType; // Измените на jakarta
import jakarta.persistence.Id; // Измените на jakarta

@Entity
public class tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private boolean completed;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }


}