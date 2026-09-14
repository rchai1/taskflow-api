package sg.edu.ntu.taskflow_api.controller;

import java.util.List;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.ntu.taskflow_api.model.Task;
import sg.edu.ntu.taskflow_api.service.TaskService;

// Controller for handling HTTP requests related to tasks
@RestController
// Base URL for all task-related endpoints
@RequestMapping("/api/tasks")
public class TaskController {
    // Service for handling task-related business logic
    private final TaskService taskService;
    private final ChatClient chatClient;
    // Constructor for injecting the TaskService
    public TaskController(TaskService taskService, ChatClient.Builder chatClientBuilder) {
    this.taskService = taskService;
    this.chatClient = chatClientBuilder.build();
}

    // Get all tasks
    @GetMapping
    // Handle GET requests to retrieve all tasks
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.findAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    // Get one task by id
    // Handle GET requests to retrieve a task by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.findTaskById(id);

        if (task == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    // Generate an AI summary of all tasks
@GetMapping("/summary")
public ResponseEntity<String> getTaskSummary() {

    List<Task> tasks = taskService.findAllTasks();

    String summary = chatClient.prompt()
            .system("""
                    You are a task management assistant.
                    Summarise the user's task list in short, plain English.
                    Clearly state what tasks are completed and what tasks are still pending.
                    Keep the response concise.
                    """)
            .user(u -> u.text("Here is the task list: {tasks}")
                    .param("tasks", tasks.toString()))
            .call()
            .content();

    return new ResponseEntity<>(summary, HttpStatus.OK);
}


    // Create a new task
    // Handle POST requests to create a new task
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    // Update an existing task
    // Handle PUT requests to update a task by its ID
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Long id,
            @RequestBody Task task) {

        Task updatedTask = taskService.updateTask(id, task);

        if (updatedTask == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    // Delete a task
    // Handle DELETE requests to delete a task by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable Long id) {
        Task deletedTask = taskService.deleteTask(id);

        if (deletedTask == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(deletedTask, HttpStatus.OK);
    }

    // Mark a task as complete
    // Handle PUT requests to mark a task as complete by its ID 
    @PutMapping("/{id}/complete")
    public ResponseEntity<Task> markTaskAsComplete(@PathVariable Long id) {
        Task completedTask = taskService.markTaskAsComplete(id);

        if (completedTask == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(completedTask, HttpStatus.OK);
    }
}