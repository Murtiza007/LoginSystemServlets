
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
    
    if(req.getParameter("ID")!=null){
    this.message=AccountsConroller.getMessage(req.getParameter("ID"));
    }
    
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
                              "<tr>"+
                                   "<td>Remember Me</td>"+
                                "<td><input type=checkbox  name='remember_me'/></td>"+
                            "</tr>"+
                
                        "</table>"+
                    "</form>"+
                      "<a href='register'>No Account ||  SignUp</a>"+
                        en.getFooter()
               );
    }
   
    
    
    @Override
    public void doGet(HttpServletRequest req ,HttpServletResponse res) throws IOException{
       AccountsConroller ac=new AccountsConroller();
       users user=new users();
       this.message="";
        this.getHtml(req, res);
 
    
    }
    @Override
     public void doPost(HttpServletRequest req ,HttpServletResponse res) throws IOException{
   
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
    }
    
}
