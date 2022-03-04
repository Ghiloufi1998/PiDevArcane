/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Skander
 */
public class MyDB {
    
    public String url ="jdbc:mysql://localhost/Arcane"; // :3306 (port mysql)
    public String user ="root";
    public String pass ="";
    public Connection connection;
    static MyDB instance;
    
    public MyDB(){
        try {
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("connexion établie");
        } catch (SQLException ex) {
            Logger.getLogger(MyDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static MyDB getInstance(){
        if(instance == null)
            instance = new MyDB();
       return instance;
    }

    public Connection getConnection() {
        return connection;
    }
    
}
