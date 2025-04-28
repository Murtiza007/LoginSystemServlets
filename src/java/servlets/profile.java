/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlets;

import Controller.AccountsConroller;
import html_essentials.essentials;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.blog_model;
import model.users;

/**
 *
 * @author HP
 */
public class profile extends HttpServlet {
    String logged_user;
    String blog="";
    String message="";
    
    
    public void getBlogpage(HttpServletRequest req ,HttpServletResponse res) throws IOException{

     PrintWriter writer=res.getWriter();
     res.setContentType("text/html");

     essentials es=new essentials();
     AccountsConroller ac=new AccountsConroller();
     users user=new users();
     blog_model bm=new blog_model();
     user.setReq(req);
     user.setRes(res);
        
        try {
            writer.print(
                    
                    es.getHeader()+
                           
                            es.getNavigation()+
                            "<p class='text-end text-secondary me-3 ' >Welcome " +ac.getLoggedUser(user)+"</p>"+
                           
                                    
                                    "<div class=container>"+
                                    "<form action='' method='POST'>"+
                                    "<h1>Post your Blog</h1>"+
                                    "<div class='form-floating'>"+
                                     "<textarea class='form-control  border-dark border-2' placeholder='Tell about your day as a tech!' name='blogtxt' style='height: 200px ;width:40%; ;'></textarea>"+
                                       "<label for='blogtxt'>Tell us whats on your mind !!</label>"+
                                    "</div>"+
                                    " <input type=submit value='POST' name='txtblog' class='btn btn-primary mt-3 '/>"+
                                    "</form>"+
                                    "<p>"+this.message+"</p><br>"+
                                     "</div>"
                            +
                            ac.getAllPosts(user)+
                            es.getFooter()
            );
        } catch (SQLException ex) {
            Logger.getLogger(profile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    @Override
    public void doGet(HttpServletRequest req ,HttpServletResponse res) throws IOException{
          this.blog="";
          this.message="";
          this.getBlogpage(req, res);
    }
    
    
     public void doPost(HttpServletRequest req ,HttpServletResponse res) throws IOException{
     
       blog_model bm=new blog_model();
       AccountsConroller ac=new AccountsConroller();
       users user=new users();
       
       user.setReq(req);
       user.setRes(res);
       
     
       
       
      bm.setBlog_text(req.getParameter("blogtxt")); 
      bm.setBlog_username(ac.getLoggedUser(user));
       
      this.blog=bm.getBlog_text();
       
      
       
      bm.setReq(req);
      bm.setRes(res);
       
      ac.SaveBlog(bm);
     
      this.message="Post Successfull";
      this.getBlogpage(req, res);
        

     
     }
}

