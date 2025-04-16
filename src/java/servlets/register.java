
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
                "<form action='' method='POST'>"+
                        "<table cellpadding=5 cellspacing=5>"+
                
                            "<tr>"+
                                "<td>SignUp Panel</td>"+
                                "<td style='color:red'>"+this.message+"</td>"+
                            "</tr>"+
                
                            "<tr>"+
                                "<td>Create Username</td>"+
                                "<td><input type=text name='txtUsername'/></td>"+
                            "</tr>"+
                
                            "<tr>"+
                                "<td>CreatePassword</td>"+
                                "<td><input type=text name='txtPassword'/></td>"+
                            "</tr>"+
                            "<tr>"+
                                "<td>Enter Email</td>"+
                                "<td><input type=text name='txtEmail'/></td>"+
                            "</tr>"+
                            "<tr>"+
                                "<td>Enter Phone Number</td>"+
                                "<td><input type=text name='txtNumber'/></td>"+
                            "</tr>"+
                
                             "<tr>"+
                                   "<td></td>"+
                                "<td><input type=submit value='SIGNUP' name='txtUsername'/></td>"+
                            "</tr>"+
                              "<tr>"+
                                   "<td><a href='home'>Already have an account || Login </a></td>"+
                             "</tr>"+
                            
                        "</table>"+
                    "</form>"+
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
