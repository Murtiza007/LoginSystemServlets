
package Controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.users;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dbContext;

public class AccountsConroller {
    
    private Connection con;
    private Statement stat;
    private ResultSet rs;
    private PreparedStatement pstat;
    
    public  AccountsConroller() throws SQLException{
        this.con=dbContext.Connect();
    }
    
    
    
    public void login(users user) throws IOException{
     
        if(isValid(user))
        {
           if(isAuthentic(user))
           {
               authorize(user);
               user.setMessage("welcome");
           }
           
        }
        else{
            user.setMessage("Please enter all fields");
        }
        
        
    
    }
    
    
    public boolean isValid(users user){
    
        return (!user.getUsername().equals("")&&
                !user.getPassword().equals(""));
    
    }
    public boolean isAuthentic(users user){
        
        try {
            String sql="select * from users where username = '"+user.getUsername()+"' and password='"+user.getPassword()+"'";
            stat=con.createStatement();
            rs=stat.executeQuery(sql);
           
            if(rs.next()){
                user.setMessage("tgy");
                return true;
            }
            else{
                user.setMessage(" incorrect");
                return false;
            }
        } catch (SQLException ex) {
           user.setMessage(ex.getMessage());
           return false;
        }
        
        
        
        
        
    }
    public void authorize(users user) throws IOException{
      
        HttpSession session=user.getReq().getSession();
        Cookie cookie=new Cookie("AUthCookie",user.getUsername());
        
        if(user.getReq().getParameter("remember_me")!=null){
        cookie.setMaxAge(60*60*24);
        cookie.isHttpOnly();
        user.getRes().addCookie(cookie);
        
        
        }
        else{
        session.setAttribute("AuthorizationSession", user.getUsername());
        
        }
        user.getRes().sendRedirect("profile");
    }
    public void isAuthorised(users user) throws IOException{
    HttpSession session=user.getReq().getSession();
    Cookie[] cookie=user.getReq().getCookies();
    
    boolean isValid=false;
    
    
        if(user.getReq().getSession().getAttribute("AuthorizationSession")!=null
                ){
           isValid=true;
            
        }  
        
        else if(cookie!=null){
            for (Cookie ck :cookie){
                if(ck.getName().equals("AUthCookie")){
                    isValid=true;
                    break;
                }      
                    
             } 
         }     

          if(isValid==false){ 
              user.getRes().sendRedirect("home?ID=101");
              
          }
        }
    
    
    
    public static String getMessage(String parameter){
        String returnValue="";
       
        if (parameter.equals("101")){
        returnValue=" you are not authorized ";
        }
        else if (parameter.equals("102")){
         returnValue=" Logged out Sucessfully ";
        }
        
      
        return returnValue;
    }
    
    
    
}
