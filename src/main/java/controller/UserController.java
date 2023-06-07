package controller;

import model.RoleModel;
import model.UserModel;
import service.RoleService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "userController",urlPatterns = {"/user", "/user/add", "/user/delete"})
public class UserController extends HttpServlet {

    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // lấy đường dẫn servlet ng dùng gọi trên browser
        String path = req.getServletPath();
        List<RoleModel> roleModelList = userService.getAllRoles();
        req.setAttribute("listRoles", roleModelList);

        switch (path) {
            case "/user":
                getAllUser(req, resp);
                break;
            case "/user/add":
                addUser(req, resp);
                break;
            case "/user/delete":
                deleteUser(req, resp);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();

        switch (path) {
            case "/user":
                break;
            case "/user/add":
                addUser(req, resp);
                break;
            case "/user/delete":
                deleteUser(req, resp);
                break;
            default:
                break;
        }

    }

    private void getAllUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserModel> list = userService.getAllUsers();

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

    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method =  req.getMethod();
        if(method.toLowerCase().equals("post")) {
            String fullname = req.getParameter("fullname");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            int role_id = Integer.parseInt(req.getParameter("role"));

            userService.insertUser(email, password, fullname, role_id);
        }

        req.getRequestDispatcher("/user-add.jsp").forward(req,resp);
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 0;
        if(req.getParameter("userid") != null) {
            id = Integer.parseInt(req.getParameter("userid"));
            userService.deleteById(id);
        }
        System.out.print(id + id > 0 ? "delete success" : "delete not success");
        req.getRequestDispatcher("/user-table.jsp").forward(req,resp);
    }
}
