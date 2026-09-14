package sg.edu.ntu.taskflow_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sg.edu.ntu.taskflow_api.model.Task;
import sg.edu.ntu.taskflow_api.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Get all tasks
    public List<Task> findAllTasks() {
        return taskRepository.findAllTasks();
    }

    // Get one task by id
    public Task findTaskById(Long id) {
        return taskRepository.findTaskById(id);
    }

    // Create a new task
    public Task createTask(Task task) {
        return taskRepository.createTask(task);
    }

    // Update an existing task
    public Task updateTask(Long id, Task task) {
        Task existingTask = taskRepository.findTaskById(id);

        if (existingTask == null) {
            return null;
        }

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setCompleted(task.isCompleted());

        return taskRepository.saveTask(existingTask);
    }

    // Delete a task
    public Task deleteTask(Long id) {
        Task existingTask = taskRepository.findTaskById(id);

        if (existingTask == null) {
            return null;
        }

        taskRepository.deleteTask(id);
        return existingTask;
    }

    // Mark a task as complete
    public Task markTaskAsComplete(Long id) {
        Task existingTask = taskRepository.findTaskById(id);

        if (existingTask == null) {
            return null;
        }

        existingTask.setCompleted(true);

        return taskRepository.saveTask(existingTask);
    }
}