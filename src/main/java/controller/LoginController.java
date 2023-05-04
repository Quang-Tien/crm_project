package controller;

import config.MysqlConfig;
import model.UserModel;
import service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "loginController",urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    public LoginService loginService = new LoginService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Cookie
//        Cookie cookie = new Cookie("username", "quangtien");
//        resp.addCookie(cookie);
//
//        Cookie[] cookies = req.getCookies();
//        for (Cookie item: cookies) {
//            if(cookie.getName().
//        }

        //check section
        HttpSession session = req.getSession();
        UserModel user = (UserModel) session.getAttribute("login_user");
        if (user != null) {
            PrintWriter writer = resp.getWriter();
            writer.println(user.getEmail());
            System.out.println(user.getFullname());
            writer.close();
        }
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }

    //tao check box, de khi ng dung quay lai, tu dong dien email va pass da dung truoc do

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* de co 1 trang web
        * B1 xác định đường dẫn
        * B2 kết nối database
        * B3 xử lý logic code
        * B4 trả dữ liệu đã đc xử lý
        *
        *
        * MVC: Model: folder tương tác database, lấy dữ liệu
        *      View: nơi xử lý logic code để ra đc data hiển thị lên giao diện
        *      Controller: nơi định nghĩa các đường dẫn
        * MVC -> mô hình cũ, lỗi thời
        *
        * mô hình thứ 2: chia theo ý nghĩa từng package
        * -> controller: chứa tất cả các file định nghĩa dường dẫn và nhận tham số từ client
        * -> Service: chứa tất cả các file xử lý logic code cho controller (links)
        * -> Repository: chứa các file đảm nhận nhiệm vụ thực hiện query to get data(ko xử lý logic code)
        * ví dụ: LoginController  LoginService  UserRepository  (hướng xử lý data)
        *                       ->            ->
        *                       <-            <-
        * */
        String email = req.getParameter("username");
        String password= req.getParameter("password");
        List<UserModel> userModelList = new ArrayList<>();

        Connection connection = null;
        try {
            String sql = "select * from users u where u.email = ? and u.password = ?";
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);


            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                //Duyệt từng dòng dữ liệu
                UserModel userModel = new UserModel();
                //Lấy giá trị của cột chỉ định và lưu vào đối tượng
                userModel.setId(resultSet.getInt("id"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setFullname(resultSet.getString("fullname"));
                userModel.setRoleId(resultSet.getInt("role_id"));

                userModelList.add(userModel);

                boolean isSuccess = userModelList.size() > 0;
                if (isSuccess) {
                    HttpSession session = req.getSession();
                    session.setAttribute("login_user", userModel);
                    System.out.println("Login success");
                }
            }

        }catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Connection-close error : " + e.getMessage());
                }
            }
        }

        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }
}
