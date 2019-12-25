package Jdbc.数据库.JDBC;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author 风
 * @date 2019/12/21 - 11:01
 */
public class JDBC {
    Connection con = null;
    String url = "jdbc:mysql://localhost:3306/StudentSystem?characterEncoding=utf-8";
    String user = "root";
    String password = "root";
    String driverClass = "com.mysql.jdbc.Driver";
    // 获取数据库连接方法
    ComboPooledDataSource ds = null;

    public Connection getCon() {
        ds = new ComboPooledDataSource();
        ds.setJdbcUrl(url);
        ds.setUser(user);
        ds.setPassword(password);
        try {
            ds.setDriverClass(driverClass);
            ds.setInitialPoolSize(5);
            ds.setMaxPoolSize(20);
            ds.setMinPoolSize(2);
            try {
                ds.setLoginTimeout(2000);
                con = ds.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return con;

    }
}
