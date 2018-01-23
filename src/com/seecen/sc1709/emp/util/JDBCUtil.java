package com.seecen.sc1709.emp.util;

import java.sql.*;

/**
 * Created by ASUS on 2018/1/16.
 */
public class JDBCUtil {
    private final static String url="jdbc:oracle:thin:@127.0.0.1:1521:orcl";
    private final static String user="hotel";
    private final static String pass="hotel";
    public static Connection getConnection(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection(url,user,pass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public static void close(Connection conn, Statement stm, ResultSet set){
        try {
            if(set!=null){
                set.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
