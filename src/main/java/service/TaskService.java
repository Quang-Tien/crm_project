package service;

import model.TaskModel;
import repository.TaskRepository;

import java.util.List;

public class TaskService {
    TaskRepository taskRepository = new TaskRepository();
    public List<TaskModel> getAllTasks() {
        return taskRepository.getAll();
    }
}
