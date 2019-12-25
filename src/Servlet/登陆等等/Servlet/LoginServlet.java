package Servlet.登陆等等.Servlet;

import Jdbc.数据库.JDBC.JDBC;
import StudentDaoImpl.具体实现.StudentDaolmpl;
import 实体类.User.Student;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 风
 * @date 2019/12/21 - 10:08
 */

public class LoginServlet extends HttpServlet {
    JDBC jdbc = new JDBC ();
    Connection con = jdbc.getCon ();
    StudentDaolmpl studao =new StudentDaolmpl ();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 编码
        request.setCharacterEncoding ("utf-8");
        //获取请求参数
        //拿到页面传过来的手动输入的验证码, 该验证码要和生成图片上的
        //本验证码比较, 如果相同, 验证码输入成功!

        String imageText = request.getParameter ("image");
        //图片的验证码
        String text = (String) request.getSession ().getAttribute ("text");
        if (!text.equalsIgnoreCase (imageText)) {
            request.setAttribute ("imageMess", "验证码输入错误!");
            request.getRequestDispatcher ("/index.jsp").forward (request, response);

        }
        //获取用户密码
        String username = request.getParameter ("username");
        String password = request.getParameter ("password");
        String sql = "select * from admin where Account=? and Password=?";
        try {
            PreparedStatement ps = con.prepareStatement (sql);
            ps.setString (1, username);
            ps.setString (2, password);
            ResultSet rs = ps.executeQuery ();
            if (rs.next () == true) {
                // 将用户信息保存到session中
                request.getSession ().setAttribute ("username", username);
                // 使用cookie实现回写用户名
                Cookie cookie = new Cookie ("username", username);
                cookie.setMaxAge (60 * 60);
                // 通过响应头发送cookie
                response.addCookie (cookie);
                List<Student> list = studao.getAllStudent();
                //把list集合添加到属性中
                request.setAttribute("stuList", list);
                // 重定向登录成功界面
            request.getRequestDispatcher ("/StudentList.jsp").forward (request,response);
            } else {
                request.setAttribute ("error", "用户名或者密码错误");
                request.getRequestDispatcher ("/index.jsp").forward (request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace ();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
