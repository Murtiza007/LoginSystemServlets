
package Controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Timestamp;
import model.users;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.blog_model;
import model.dbContext;

public class AccountsConroller {
    
    private Connection con;
    private Statement stat;
    private ResultSet rs;
    private PreparedStatement pstat;
    
    
    
    public AccountsConroller(){
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
        
       try{ 
       String sql="select * from users where username = '"+user.getUsername()+"' and password='"+user.getPassword()+"'";
       stat=con.createStatement();
       rs=stat.executeQuery(sql);
       
       if(rs.next()){
           return true;
       }
       else{
           user.setMessage("Incorrect credentials");
           return false;
       }
       
       }
       catch (SQLException ex){
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
    
    
    public boolean isAuthorised(users user) throws IOException{
    HttpSession session=user.getReq().getSession();
    Cookie[] cookie=user.getReq().getCookies();
    
    boolean isValid=false;
    
    
    if(user.getReq().getSession().getAttribute("AuthorizationSession")!=null){
           isValid=true;
            return true;
    }  
        
    else if(cookie!=null){
            for (Cookie ck :cookie){
                if(ck.getName().equals("AUthCookie")){
                    isValid=true;
                    return true;
                    
                }      
                  
             } 
             //return true; 
         }     

          if(isValid==false){ 
              user.getRes().sendRedirect("home?ID=101");
              //return false;
          }
          return false;
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
    
     public String getLoggedUser(users user){
     
     HttpSession session=user.req.getSession();
     Cookie[] cookie=user.getReq().getCookies();
         
        if(user.getReq().getSession().getAttribute("AuthorizationSession")!=null){
           return session.getAttribute("AuthorizationSession").toString();
        }  
        else  if(cookie!=null){
            for (Cookie ck :cookie){
                if(ck.getName().equals("AUthCookie")){
                    return ck.getValue();
                    
                }    }  }
        return null;
     
     }
    
    
    public void SaveUser(users user) throws IOException{
        if(isValidReg(user)){
            if(checkuser(user)){
            
            if(register(user)){
             user.setMessage("User Registered. Now Login into your new account" );
             
            }
            else{
            user.setMessage("signup failed");
            }
            }
            else{
            user.setMessage("username not available");
            }
        }
        else{
         user.setMessage("Please fill in all required fields.");
       
        }
    }
    
    
    
    
    public boolean isValidReg(users user){
    
    if (user.getUsername().isEmpty() ||
        user.getPassword().isEmpty() ||
        user.getEmail().isEmpty() ||
        user.getPhone().isEmpty())
    {
       return false;
    }
    return true;
    }

    public boolean checkuser(users user){
     try{ 
       String sql="select * from users where username = '"+user.getUsername()+"' ";
       stat=con.createStatement();
       rs=stat.executeQuery(sql);
       
       if(rs.next()){
           return false;
       }
       else{
           user.setMessage("Incorrect credentials");
           return true;
       }
       
       }
       catch (SQLException ex){
       user.setMessage(ex.getMessage());
       return false;
       }
        
    
    }
    
    public boolean register(users user) {
          
       try {
        
        String sql = "INSERT INTO users (username, password, email, phone) VALUES ('" 
           + user.getUsername() + "', '" 
           + user.getPassword() + "', '" 
           + user.getEmail() + "', '" 
           + user.getPhone() + "')";
           
        pstat= con.prepareStatement(sql);
         
             int rows = pstat.executeUpdate();
             if (rows > 0) {
                 return true;
            } else {
                 return false;
            }
        } catch (SQLException ex) {
            user.setMessage(ex.getMessage());
            return false;
        }

    }
    public boolean SaveBlog(blog_model bm) throws IOException{
        users user =new users();
    try{
      String sql = "INSERT INTO blogs (username, blog) VALUES ('" 
      + bm.getBlog_username() + "', '" 
      + bm.getBlog_text()+ "')";
      
   
      
     pstat= con.prepareStatement(sql);
        
             int rows = pstat.executeUpdate();
             if (rows > 0) {
            
          
             return true;
            } else {
                user.setMessage("Posted Error");
               
                
                return false;
                 
            }
        }
        catch (SQLException ex) {
       
           return false;
        }
 
         
    }
    public String getAllPosts(users user) throws SQLException{
     blog_model bm=new blog_model();
     
     StringBuilder content=new StringBuilder();
     
     
     String post_content="";
     String post_user;
     String post_timeDate;
        try {
       String sql="SELECT * FROM blogs ORDER BY blog_id DESC ";
      //String sql = "SELECT * FROM blogs WHERE username = '" + getLoggedUser(user) + "' ORDER BY blog_id DESC";

       stat=con.createStatement();
       rs=stat.executeQuery(sql);
         
     
          while (rs.next()) {
             post_content =rs.getString("blog");
             post_user=rs.getString("username");
             post_timeDate=rs.getString("timestamp");
             
                content.append("<div class='card mb-4 shadow-sm p-3 w-75 mx-auto'>");
                content.append("<div class='card-body'>");
                content.append("<h4 class='card-title text-primary'> " + post_user + "</h4>");
                content.append("<p class='card-subtitle mb-2 text-muted'>" + post_timeDate + "</p>");
                content.append("<p class='card-text'>" + post_content + "</p>");
                content.append("</div>");
                content.append("</div>");
    
           }
        } catch (SQLException ex) {
            post_content="fail";
        }
        return content.toString();
    }
    
    
    
}
