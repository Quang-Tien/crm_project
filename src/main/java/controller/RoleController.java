package controller;

import model.RoleModel;
import service.RoleTableService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "roleController", urlPatterns = {"/role"})
public class RoleController extends HttpServlet {

    RoleTableService roleTableService = new RoleTableService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RoleModel> roleModelList = roleTableService.getAllRoles();
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
}
