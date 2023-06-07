package service;

import model.JobModel;
import repository.JobRepository;

import java.util.Date;
import java.util.List;

public class JobService {
    JobRepository jobRepository = new JobRepository();
    public List<JobModel> getAllJobs() {
        return jobRepository.getAll();
    }

    public boolean insertJob(String name, Date startDate, Date endDate) {
        return jobRepository.insertJob(name, startDate, endDate);
    }

    public boolean deleteJob(int id) {
        return jobRepository.deleteJob(id);
    }
}
