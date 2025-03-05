
package servlets;

import Controller.AccountsConroller;
import html_essentials.essentials;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import model.users;


public class home extends HttpServlet{
   
String message="";    
    
    public void getHtml(HttpServletRequest req ,HttpServletResponse res) throws IOException{
    PrintWriter writer=res.getWriter();
    essentials en=new essentials();
        res.setContentType("text/html");
        writer.print(
                    en.getHeader()+
                    "<form action='' method='POST'>"+
                        "<table cellpadding=5 cellspacing=5>"+
                
                            "<tr>"+
                                "<td>Login Panel</td>"+
                                "<td style='color:red'>"+this.message+"</td>"+
                            "</tr>"+
                
                            "<tr>"+
                                "<td>Enter Username</td>"+
                                "<td><input type=text name='txtUsername'/></td>"+
                            "</tr>"+
                
                            "<tr>"+
                                "<td>Enter Password</td>"+
                                "<td><input type=text name='txtPassword'/></td>"+
                            "</tr>"+
                
                             "<tr>"+
                                   "<td></td>"+
                                "<td><input type=submit value='LOGIN' name='txtUsername'/></td>"+
                            "</tr>"+
                
                        "</table>"+
                    "</form>"+
                      "<a href='register'>No Account ||  SignUp</a>"+
                        en.getFooter()
               );
    }
   
    
    
    @Override
    public void doGet(HttpServletRequest req ,HttpServletResponse res) throws IOException{
    this.getHtml(req, res);
    this.message="";
    }
    @Override
     public void doPost(HttpServletRequest req ,HttpServletResponse res) throws IOException{
   
    PrintWriter writer=res.getWriter();
        res.setContentType("text/html");
        
        users user=new users();
        
        user.setUsername(req.getParameter("txtUsername"));
        user.setPassword(req.getParameter("txtPassword"));
        
       AccountsConroller ac=new AccountsConroller();
       user.setReq(req);
       user.setRes(res);
       
        ac.login(user);
        this.message=user.getMessage();
        
        
        this.getHtml(req, res);
    }
    
}
