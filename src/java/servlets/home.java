
package servlets;

import Controller.AccountsConroller;
import html_essentials.essentials;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dbContext;

import model.users;


public class home extends HttpServlet{
   
String message="";    
    
    public void getHtml(HttpServletRequest req ,HttpServletResponse res) throws IOException, SQLException{
    PrintWriter writer=res.getWriter();
    
    if(req.getParameter("ID")!=null){
    this.message=AccountsConroller.getMessage(req.getParameter("ID"));
    }
    
    dbContext db=new dbContext();
    dbContext.Connect();
    
    essentials en=new essentials();
        res.setContentType("text/html");
        writer.print(
                    en.getHeader()
        +"<p class='h2 text-center mt-5 text-Primary fw-bold' >WordNest</p>"
        +"<div class='container mt-5'>"
        + "<div class='row justify-content-center'>"
        + "<div class='col-md-4'>"
        + "<div class='card shadow-sm'>"
        + "<div class='card-header  text-black text-center'><h5 class='mb-0'>Login Panel</h5></div>"
        + "<div class='card-body'>"
        + "<form method='POST'>"

        // Error message
        + "<div class='mb-3 text-danger'>" + this.message + "</div>"

        // Username
        + "<div class='mb-3'>"
        + "<label for='txtUsername' class='form-label'>Enter Username</label>"
        + "<input type='text' class='form-control' name='txtUsername' id='txtUsername' required>"
        + "</div>"

        // Password
        + "<div class='mb-3'>"
        + "<label for='txtPassword' class='form-label'>Enter Password</label>"
        + "<input type='password' class='form-control' name='txtPassword' id='txtPassword' required>"
        + "</div>"

        // Remember me
        + "<div class='form-check mb-3'>"
        + "<input class='form-check-input' type='checkbox' name='remember_me' id='remember_me'>"
        + "<label class='form-check-label' for='remember_me'>Remember Me</label>"
        + "</div>"

        // Submit button
        + "<button type='submit' class='btn btn-primary w-100'>LOGIN</button>"

        + "</form>"
        + "<div class='mt-3 text-center'>"
        + "<a href='register'>No Account? Sign Up</a><br>"+
                dbContext.message+
        "</div>"
        + "</div>" // card-body
        + "</div>" // card
        + "</div>" // col
        + "</div>" // row
        + "</div>"+
                           
                        en.getFooter()
               );
    }
   
    
    
    @Override
    public void doGet(HttpServletRequest req ,HttpServletResponse res) throws IOException{
    
    
    
    try {  
        AccountsConroller ac=new AccountsConroller();
        users user=new users();
        this.message="";
        this.getHtml(req, res);
          
        
    } catch (SQLException ex) {
        PrintWriter writer=res.getWriter();
        res.setContentType("text/html");
        writer.print("coming from catch");
        
    }
    
   
        
        
        
    
 
    
    }
    @Override
     public void doPost(HttpServletRequest req ,HttpServletResponse res) throws IOException{
   
    try {
        
        PrintWriter writer=res.getWriter();
        res.setContentType("text/html");
        
        users user=new users();
        user.setRememberme(req.getParameter("remember_me"));
        user.setUsername(req.getParameter("txtUsername"));
        user.setPassword(req.getParameter("txtPassword"));
        
        
        
        AccountsConroller ac=new AccountsConroller();
        user.setReq(req);
        user.setRes(res);
        
        ac.login(user);
        this.message=user.getMessage();
        
        
        this.getHtml(req, res);
    } catch (SQLException ex) {
   }
    
}}
