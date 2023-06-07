package controller;

import model.RoleModel;
import service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "roleController", urlPatterns = {"/role", "/role/add", "/role/delete"})
public class RoleController extends HttpServlet {
    RoleService roleService = new RoleService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();

        switch (path) {
            case "/role":
                getAllRoles(req, resp);
                break;
            case "/role/add":
                addRole(req, resp);
                break;
            case "/role/delete":
                deleteRole(req, resp);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();

        switch (path) {
            case "/role":
                getAllRoles(req, resp);
                break;
            case "/role/add":
                addRole(req, resp);
                break;
            case "/role/delete":
                deleteRole(req, resp);
                break;
            default:
                break;
        }
    }

    public void getAllRoles(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RoleModel> roleModelList = roleService.getAllRoles();
        if(roleModelList.size() > 0) {
            for (RoleModel role : roleModelList) {
                System.out.println(role.getName());
            }
        }
        else {
            System.out.println("RoleModelList is empty");
        }

        req.setAttribute("listRoles", roleModelList);

        req.getRequestDispatcher("role-table.jsp").forward(req, resp);
    }

    public void addRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        boolean isSuccess = false;
        if(method.toLowerCase().equals("post")) {
            String roleName = req.getParameter("name");
            String roleDesc = req.getParameter("desc");

            isSuccess = roleService.insertRole(roleName, roleDesc);
        }

        req.getRequestDispatcher("/role-add.jsp").forward(req, resp);
    }

    public void deleteRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("roleid"));
        boolean isSuccess = roleService.deleteRole(id);

        System.out.print( "Delete role success ? " + isSuccess);

        req.getRequestDispatcher("/role-table.jsp").forward(req, resp);
    }
}
