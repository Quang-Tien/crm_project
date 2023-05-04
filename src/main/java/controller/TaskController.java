package controller;

import model.JobModel;
import model.TaskModel;
import service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "taskController", urlPatterns = {"/task"})
public class TaskController extends HttpServlet {
    TaskService taskService = new TaskService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
