/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author bensa
 */
public class MyDB {
    String url = "jdbc:mysql://localhost:3306/PI";
    String user = "root";
    String pwd = "";
    Connection conn;
 
    
    public MyDB(){
        try {
        conn=DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion etablie");
        } catch (SQLException ex) {
           System.err.println(ex.getMessage());
        }
    }

    public  Connection getConn() {
        return conn;
    }
    
}
