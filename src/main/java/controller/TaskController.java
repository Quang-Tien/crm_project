package controller;

import model.JobModel;
import model.TaskModel;
import model.UserModel;
import service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "taskController", urlPatterns = {"/task", "/task/add",  "/task/delete"})
public class TaskController extends HttpServlet {
    TaskService taskService = new TaskService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        List<JobModel> jobLists = taskService.getAllJobs();
        List<UserModel> userLists = taskService.getAllUsers();
        req.setAttribute("listJobs", jobLists);
        req.setAttribute("listUsers", userLists);
        switch (path) {
            case "/task":
                getAll(req, resp);
                break;
            case "/task/add":
                try {
                    addTask(req, resp);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/task/delete":
                deleteTask(req, resp);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/task":
                getAll(req, resp);
                break;
            case "/task/add":
                try {
                    addTask(req, resp);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/task/delete":
                deleteTask(req, resp);
                break;
            default:
                break;
        }
    }

    protected void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TaskModel> taskModelList = taskService.getAllTasks();
        if(taskModelList.size() > 0) {
            for (TaskModel task : taskModelList) {
                System.out.println(task.getName());
            }
        }
        else {
            System.out.println("taskModelList is empty");
        }

        req.setAttribute("listTasks", taskModelList);

        req.getRequestDispatcher("task.jsp").forward(req, resp);
    }

    private void addTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
        String method = req.getMethod();
        SimpleDateFormat format = new SimpleDateFormat("mm/dd/yyyy");

        if(method.toLowerCase().equals("post")) {
            int jobId = Integer.parseInt(req.getParameter("jobId"));
            String taskName = req.getParameter("name");
            int userId = Integer.parseInt(req.getParameter("userId"));
            Date startDate = format.parse(req.getParameter("startDate"));
            Date endDate =  format.parse(req.getParameter("endDate"));

            taskService.insertTask(taskName, jobId, userId, startDate, endDate);
        }

        req.getRequestDispatcher("/task-add.jsp").forward(req, resp);
    }

    public void deleteTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("taskid"));
        boolean isSuccess = taskService.deleteTask(id);

        System.out.print( "Delete task success ? " + isSuccess);

        req.getRequestDispatcher("/task.jsp").forward(req, resp);
    }
}
