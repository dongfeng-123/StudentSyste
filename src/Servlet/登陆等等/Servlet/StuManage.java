package Servlet.登陆等等.Servlet;

import StudentDaoImpl.具体实现.StudentDaolmpl;
import 实体类.User.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author 风
 * @date 2019/12/24 - 15:26
 */
@WebServlet("*.do")
public class StuManage extends HttpServlet {
    StudentDaolmpl studao = new StudentDaolmpl ();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet (request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取路径
        String path = request.getServletPath ();
        System.out.println ("路径是 = " + path);
        //获取后再处理路径
        String string = path.substring (1, path.length () - 3);
        System.out.println ("处理后的路径 = " + string);
        if (string.equals ("query")) {
            query (request, response);
            //增加
        } else if (string.equals ("add")) {
            add (request, response);
            //条件查询
        } else if (string.equals ("querOne")) {
            querOne (request, response);
            //修改
        } else if (string.equals ("updateDate")) {
            updateDate (request, response);
        } else if (string.equals ("update")) {
            update (request, response);
            //删除
        } else if (string.equals ("delete")) {
            delete (request, response);
        } else {
            System.out.println ("...........");
        }
    }


    //删除方法
    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sid = request.getParameter ("id");
        int id = Integer.parseInt (sid);
        studao.delete (id);
        response.sendRedirect ("query.do");
    }

    //查询全部
    private void Allthequery(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding ("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace ();
        }
        List<Student> list = null;
        String sq = "select * from user";

    }

    //修改方法
    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter ("id");
        HttpSession session = request.getSession ();
        session.setAttribute ("updateId", id);
        request.getRequestDispatcher ("/Update.jsp").forward (request, response);
        System.out.println (request.getParameter ("id"));
    }

    private void updateDate(HttpServletRequest request, HttpServletResponse response) {
        try {

            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession();
        Object sid = session.getAttribute("updateId");
        Integer id = Integer.parseInt((String) sid);
        System.out.println("id = " + id);
        String name = request.getParameter("name");
        String age = request.getParameter("age");
       String sex = request.getParameter("sex");
       Integer aa = Integer.parseInt (age);
        Student stu = new Student(id, name,sex, aa);
        try {
            studao.updateDate(stu);
            response.sendRedirect("query.do");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //条件查询的查询全部
    private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取到list集合
        List<Student> list = studao.getAllStudent ();
        //把list集合添加到属性中
        request.setAttribute ("stuList", list);
        //利用转发将数据发送到jsp页面
        request.getRequestDispatcher ("/StudentList.jsp").forward (request, response);

    }

    //条件查询
    private void querOne(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding ("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace ();
        }
        List<Student> list = null;
        String stuid = request.getParameter ("stuid");
        String name = request.getParameter ("stuName");
        System.out.println ("name = " + name);
        String sql = "select * from user";
        //判断输入id是否为空
        if ((name == null || "".equals (name)) && (stuid == null || "".equals (stuid))) {
            //如果什么都不填，就是查询全部
            try {
                query (request, response);
            } catch (ServletException e) {
                e.printStackTrace ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
            //判断id为空
        } else if (stuid == null || "".equals (stuid)) {
            sql += " where name='" + name + "'";
            System.out.println ("sql = " + sql);
            list = studao.queryOne (sql);
            request.setAttribute ("stuList", list);
            //利用转发将数据发送到jsp页面
            try {
                request.getRequestDispatcher ("/StudentList.jsp").forward (request, response);
            } catch (ServletException e) {
                e.printStackTrace ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
            //name为空
        } else if (name == null || "".equals (name)) {
            int sid = Integer.parseInt (stuid);
            sql += " where id=" + sid;
            System.out.println ("sid = " + sql);
            list = studao.queryOne (sql);
            request.setAttribute ("stuList", list);
            //利用转发将数据发送到jsp页面
            try {
                request.getRequestDispatcher ("/StudentList.jsp").forward (request, response);
            } catch (ServletException e) {
                e.printStackTrace ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
            //id和name同时不为空
        } else {
            int sid = Integer.parseInt (stuid);
            sql += " where id=" + sid + " and name='" + name + "'";
            list = studao.queryOne (sql);
            request.setAttribute ("stuList", list);
            //利用转发将数据发送到jsp页面
            try {
                request.getRequestDispatcher ("/StudentList.jsp").forward (request, response);
            } catch (ServletException e) {
                e.printStackTrace ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
    }


    //增加
    private void add(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding ("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace ();
        }
        String name = request.getParameter ("name");
        String sex = request.getParameter ("sex");
        String sage = request.getParameter ("age");
        int age = Integer.parseInt (sage);
        studao.add (name, sex, age);
        try {
            response.sendRedirect ("query.do");
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

}
