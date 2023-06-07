package service;

import model.JobModel;
import model.TaskModel;
import model.UserModel;
import repository.JobRepository;
import repository.TaskRepository;
import repository.UserRepository;

import java.util.Date;
import java.util.List;

public class TaskService {
    TaskRepository taskRepository = new TaskRepository();
    JobRepository jobRepository = new JobRepository();
    UserRepository userRepository = new UserRepository();
    public List<TaskModel> getAllTasks() {
        return taskRepository.getAll();
    }

    public List<JobModel> getAllJobs() {
        return jobRepository.getAll();
    }
    public List<UserModel> getAllUsers() {
        return userRepository.getAll();
    }

    public boolean insertTask(String name, int jobId, int userId, Date startDate, Date endDate) {
        return taskRepository.insertTask(name, jobId, userId, startDate, endDate);
    }


    public boolean deleteTask(int id) {
        return taskRepository.deleteTask(id);
    }
}
