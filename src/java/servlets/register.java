
package servlets;

import Controller.AccountsConroller;
import html_essentials.essentials;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import model.users;

@WebServlet(urlPatterns={"/register"})
public class register extends HttpServlet {
    String message="";
    public void register(HttpServletRequest req ,HttpServletResponse res) throws IOException{
        essentials es=new essentials();
        PrintWriter writer =res.getWriter();
        res.setContentType("text/html");
        writer.print(
                es.getHeader()+
        "<p class='h2 text-center mt-4 text-Primary fw-bold' >WordNest</p>"       
                        
        +"<div class='container mt-4 mb-3'>"
        + "<div class='row justify-content-center'>"
        + "<div class='col-md-6'>"
        + "<div class='card shadow-sm'>"
        + "<div class='card-header  text-white text-center bg-primary'><h5 class='mb-0'>Sign up</h5></div>"
        + "<div class='card-body'>"
        + "<form method='POST'>"

        // Error message
        + "<div class='mb-3 text-danger'>" + this.message + "</div>"

        // Username
        + "<div class='mb-3'>"
        + "<label for='txtUsername' class='form-label'>Create Username</label>"
        + "<input type='text' class='form-control' name='txtUsername' id='txtUsername' required>"
        + "</div>"

        // email
        + "<div class='mb-3'>"
        + "<label for='txtPassword' class='form-label'>Enter Email</label>"
        + "<input type='email' class='form-control' name='txtEmail' id='txtEmail' required>"
        + "</div>"
                
          + "<div class='mb-3'>"
        + "<label for='txtUsername' class='form-label'>Enter Phone</label>"
        + "<input type='phone' class='form-control' name='txtNumber' id='txtNumber' required>"
        + "</div>"

        // phone
        + "<div class='mb-3'>"
        + "<label for='txtPassword' class='form-label'>Create Password</label>"
        + "<input type='password' class='form-control' name='txtPassword' id='txtPassword' required>"
        + "</div>"       

        

        // Submit button
        + "<button type='submit' class='btn btn-primary w-100'>Signup</button>"

        + "</form>"
        + "<div class='mt-3 text-center'>"
        + "<a href='home'>Already have an account | Login</a><br>"+
                
        "</div>"
        + "</div>" // card-body
        + "</div>" // card
        + "</div>" // col
        + "</div>" // row
        + "</div>"+
                es.getFooter());
        
    }
    
    
    @Override
    public void doGet(HttpServletRequest req ,HttpServletResponse res) throws IOException{
    
    this.message="";
    this.register(req, res);
    }
     @Override
    public void doPost(HttpServletRequest req ,HttpServletResponse res) throws IOException{
    
     PrintWriter writer=res.getWriter();
        res.setContentType("text/html");
        
        users user=new users();
       
        
        user.setUsername(req.getParameter("txtUsername"));
        user.setPassword(req.getParameter("txtPassword"));
        user.setEmail(req.getParameter("txtEmail"));
        user.setPhone(req.getParameter("txtNumber"));
        
        
        
        

        AccountsConroller ac=new AccountsConroller();
        user.setReq(req);
        user.setRes(res);
        ac.SaveUser(user);
        this.message=user.getMessage();
        this.register(req, res);
    }
    
}
