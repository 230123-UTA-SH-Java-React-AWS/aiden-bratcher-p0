package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
    private static Connection con;

    private ConnectionUtils(){
        con = null;
    }

    public static Connection getConnection(){
        try {
            if(con != null && !con.isClosed()){
                return con;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String url, user, pass;

        url = System.getenv("url");
        user = System.getenv("user");
        pass = System.getenv("pass");

        try {
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("YOU PROBABLY GAVE THE WRONG PASSWORD/URL");
        }
        
        return con;
    }
}
