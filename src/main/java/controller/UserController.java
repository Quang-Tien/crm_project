package controller;

import model.UserModel;
import service.UserTableService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "userController",urlPatterns = {"/user"})
public class UserController extends HttpServlet {
    UserTableService userTableService = new UserTableService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserModel> list = userTableService.getAllUsers();
        if (list.size() > 0) {
            for (UserModel user: list) {
                System.out.println(user.getEmail());
            }
        }
        else {
            System.out.println("List Empty");
        }
        req.setAttribute("listUsers", list);

        req.getRequestDispatcher("user-table.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("user-table.jsp").forward(req,resp);
    }
}
