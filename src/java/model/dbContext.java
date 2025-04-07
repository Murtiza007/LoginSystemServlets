/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class dbContext {
    public static String messgae="";
    public static Connection Connect()  {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb","root","");
            dbContext.messgae="Connected";
            return con;
        } catch (ClassNotFoundException | SQLException  ex) {
            dbContext.messgae="Connection failed";
            return null;
        }
    
    }
    
    public static void close( Connection  con){
        try {
            con.close();
        } catch (SQLException ex) {
            dbContext.messgae="Connection closed failed";
        }
    }
    
}
