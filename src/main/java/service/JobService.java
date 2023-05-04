package service;

import model.JobModel;
import repository.JobRepository;

import java.util.List;

public class JobService {
    JobRepository jobRepository = new JobRepository();
    public List<JobModel> getAllJobs() {
        return jobRepository.getAll();
    }
}
