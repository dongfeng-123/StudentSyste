package StudentDaoImpl.具体实现;

import Jdbc.数据库.JDBC.JDBC;
import com.dao.用户相关业务类.StudentDao;
import 实体类.User.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 风
 * @date 2019/12/24 - 15:24
 */
public class StudentDaolmpl implements StudentDao {
    JDBC jdbc = new JDBC ();
    Connection con = jdbc.getCon ();

    //查询全部的
    @Override
    public List<Student> getAllStudent() {
        List<Student> list = new ArrayList<> ();
        String sql = "select * from user";
        try {
            PreparedStatement ps = con.prepareStatement (sql);
            ResultSet rs = ps.executeQuery ();
            while (rs.next ()) {
                int id = rs.getInt ("id");
                String name = rs.getString ("name");
                String age = rs.getString ("age");
                int sex = rs.getInt ("sex");
                Student stu = new Student (id, name, age, sex);
                list.add (stu);
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
        return list;
    }

    //条件查询
    @Override
    public List<Student> queryOne(String sql) {
        List<Student> list = new ArrayList ();
        try {
            PreparedStatement ps = con.prepareStatement (sql);
            ResultSet rs = ps.executeQuery ();
            while (rs.next ()) {
                int id = rs.getInt ("id");
                String name = rs.getString ("name");
                String age = rs.getString ("age");
                int sex = rs.getInt ("sex");
                // 把查询的数据设置到student对象
                // 创建带参数构造器值直接添加到student对象
                Student stu = new Student (id, name, age, sex);
                // 把student对象添加到list集合
                list.add (stu);
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }

        return list;
    }


    //增加
    @Override
    public void add(String name, String age, int sex) {
        String add = "insert into user(name,age,sex) values(?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement (add);

            ps.setString (1, name);
            ps.setString (2, age);
            ps.setInt (3, sex);
            int sum = ps.executeUpdate ();
            System.out.println (" 一共 " + ps + "受到影响");
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    //删除方法
    @Override
    public void delete(int id) {
        String delete1 = "delete from user where id=?";
        try {
            PreparedStatement ps = con.prepareStatement (delete1);
            ps.setInt (1, id);
            int sum = ps.executeUpdate ();
            System.out.println ("数据库" + sum + "受到影响");
        } catch (SQLException e) {
            e.printStackTrace ();
        }


    }

    // 修改方法
    @Override
    public void updateDate(Student stu) {
        String update = "update user set name=?,age=?,sex=? where id=?";
        try {
            PreparedStatement ps = con.prepareStatement (update);
            ps.setString (1, stu.getName ());
            ps.setString (2, stu.getAge ());
            ps.setInt (3, stu.getSex ());
            ps.setInt (4, stu.getId ());
            int su = ps.executeUpdate ();
            System.out.println ("数据库受到影响 " + su);
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }


}


