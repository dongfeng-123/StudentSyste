package StudentDaoImpl.具体实现;

import Jdbc.数据库.JDBC.JDBC;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 风
 * @date 2019/12/24 - 17:33
 */

public class Register extends HttpServlet {
    JDBC jdbc = new JDBC ();
    Connection con = jdbc.getCon ();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String account = request.getParameter ("Account");
            String password = request.getParameter ("Password");
            String isExit = "select Account from admin where Account=?";
            PreparedStatement p = con.prepareStatement (isExit);
            p.setString (1, account);

            ResultSet rs = p.executeQuery ();
            if (rs.next () == true) {

                request.setAttribute ("error1", "用户名已存在,请重新输入");
                request.getRequestDispatcher ("/admin.jsp").forward (request, response);
            } else {
                String sql = "insert into admin(Account,Password) values(?,?)";

                PreparedStatement ps = con.prepareStatement (sql);
                ps.setString (1, account);
                ps.setString (2, password);
                int a = ps.executeUpdate ();
                System.out.println ("一共" + a + "行受到影响");
                request.getRequestDispatcher ("index.jsp").forward (request, response);
                System.out.println ("注册成功");
            }

        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
