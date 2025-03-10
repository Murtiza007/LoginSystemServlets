/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlets;

import Controller.AccountsConroller;
import html_essentials.essentials;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import model.users;

/**
 *
 * @author HP
 */
public class profile extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req ,HttpServletResponse res) throws IOException{
        PrintWriter writer=res.getWriter();
        res.setContentType("text/html");
        AccountsConroller ac=new AccountsConroller();
        users user=new users();
        
        user.setReq(req);
        user.setRes(res);
        
        ac.isAuthorised(user);
        essentials es=new essentials();
        writer.print(
                
                es.getHeader()+
                
                "welcome to profile"+
                        "<br>"+
                  "<a href='logout'>Logout here</a>"+
                        
                 es.getFooter()
                        );
        
        
    }
}

