package sg.edu.ntu.taskflow_api.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import sg.edu.ntu.taskflow_api.model.Task;

// Repository for managing tasks in-memory
@Repository
// This class provides CRUD operations for tasks using an in-memory data store.
public class TaskRepository {

    // In-memory storage for tasks
    private final Map<Long, Task> tasks = new HashMap<>();
    // Next ID to be assigned to a new task
    private Long nextId = 1L;

    // Get all tasks
    public List<Task> findAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    // Get one task by id
    public Task findTaskById(Long id) {
        return tasks.get(id);
    }

    // Create a task, simple counter
    public Task createTask(Task task) {
        task.setId(nextId);
        nextId++;

        tasks.put(task.getId(), task);

        return task;
    }

    // Save an existing task
    public Task saveTask(Task task) {
        tasks.put(task.getId(), task);
        return task;
    }

    // Delete a task
    public void deleteTask(Long id) {
        tasks.remove(id);
    }
}