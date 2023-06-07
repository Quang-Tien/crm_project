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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "jobController", urlPatterns = {"/job", "/job/add", "/job/delete"})
public class JobController extends HttpServlet {
    JobService jobService = new JobService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/job":
                getAll(req, resp);
                break;
            case "/job/add":
                try {
                    addJob(req, resp);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/job/delete":
                deleteJob(req, resp);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/job":
                getAll(req, resp);
                break;
            case "/job/add":
                try {
                    addJob(req, resp);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/job/delete":
                deleteJob(req, resp);
                break;
            default:
                break;
        }
    }

    public void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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


    public void addJob(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
        String method = req.getMethod();
        SimpleDateFormat format = new SimpleDateFormat("mm/dd/yyyy");

        if(method.toLowerCase().equals("post")) {
            String jobName = req.getParameter("name");
            Date startDate = format.parse(req.getParameter("startDate"));
            Date endDate =  format.parse(req.getParameter("endDate"));

            jobService.insertJob(jobName, startDate, endDate);
        }

        req.getRequestDispatcher("/groupwork-add.jsp").forward(req, resp);
    }

    public void deleteJob(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("jobid"));
        boolean isSuccess = jobService.deleteJob(id);

        System.out.print( "Delete job success ? " + isSuccess);

        req.getRequestDispatcher("/groupwork.jsp").forward(req, resp);
    }
}
