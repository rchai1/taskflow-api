# TaskFlow API

TaskFlow API is a task management REST API developed for the Module 3 End of Module Assignment.

The application is built with Java and Spring Boot and demonstrates RESTful API development, layered application architecture, dependency injection, in-memory data storage, and Spring AI integration.

## Technologies Used

- Java 21
- Spring Boot
- Spring Web MVC
- Spring AI
- OpenAI API
- Lombok
- Maven

## Application Structure

The application follows a layered architecture:

- `model` – Defines the Task data model
- `repository` – Handles in-memory task storage and CRUD operations
- `service` – Contains task-related business logic
- `controller` – Handles REST API requests and responses

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/tasks` | Get all tasks |
| GET | `/api/tasks/{id}` | Get a task by ID |
| POST | `/api/tasks` | Create a new task |
| PUT | `/api/tasks/{id}` | Update an existing task |
| DELETE | `/api/tasks/{id}` | Delete a task |
| PUT | `/api/tasks/{id}/complete` | Mark a task as completed |
| GET | `/api/tasks/summary` | Generate an AI summary of completed and pending tasks |

## Task Data

A task contains the following fields:

```json
{
  "id": 1,
  "title": "Complete Module 3 assignment",
  "description": "Finish and submit the TaskFlow API",
  "completed": false
}