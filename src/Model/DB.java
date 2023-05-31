/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Yahya
 */
public class DB {
    private static DB db;
    private DB(){
        
    }
    
    public static DB getInstance(){
        if (db == null) {
            db = new DB();
            return db;
        }else{
           return db; 
        }
    }
    
    public Connection getConnection() throws SQLException{
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");
        return c;
    }
}
