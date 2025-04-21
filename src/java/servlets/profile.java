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
                            
                            "welcome " +ac.getLoggedUser(user)+" to profile"+"<br>"+
                                    "<button> <a href='logout'>Logout</a></button>"+
                                    
                                    "<form action='' method='POST'>"+
                                    "<h1>Post your Blog</h1>"+
                                    "<textarea name='blogtxt'></textarea>"+ "<br>"+
                                    " <input type=submit value='POST' name='txtblog'/>"+
                                    "</form>"
                            +this.message+
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

