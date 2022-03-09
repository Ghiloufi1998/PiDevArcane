/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



 
  //@author souha saffar
public class DB {
    private String url ="jdbc:mysql://localhost/testdb"; // :3306 (port mysql)
    private String user ="root";
    private String pass ="";
    private Connection cnx;
    static DB instance;
    
    public DB(){
        try {
            cnx = DriverManager.getConnection(url, user, pass);
            System.out.println("connexion établie");
        } catch (SQLException ex) {
           System.err.println(ex.getMessage());
        }
    }
    
    
    public static DB getInstance(){
        if(instance == null)
            instance = new DB();
       return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
}
