package controller;

import model.JobModel;
import model.RoleModel;
import service.JobService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "jobController", urlPatterns = {"/job"})
public class JobController extends HttpServlet {
    JobService jobService = new JobService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<JobModel> jobModelList = jobService.getAllJobs();
        if(jobModelList.size() > 0) {
            for (JobModel job : jobModelList) {
                System.out.println(job.getName());
            }
        }
        else {
            System.out.println("jobModelList is empty");
        }

        req.setAttribute("listJobs", jobModelList);

        req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
    }
}
