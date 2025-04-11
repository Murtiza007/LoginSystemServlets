/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package servlets;

import Controller.AccountsConroller;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.users;

/**
 *
 * @author HP
 */
public class Authorzise_filter implements Filter {
    
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured.
    private FilterConfig filterConfig = null;
    
    public Authorzise_filter() {
    }
    
     @Override
    public void init(FilterConfig filterConfig) {
      
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        AccountsConroller ac=new AccountsConroller();
        users user=new users();
        
        HttpServletRequest req=(HttpServletRequest)request;
        HttpServletResponse res=(HttpServletResponse)response;
        
        user.setReq(req);
        user.setRes(res);
        
        
        if(ac.isAuthorised(user)){
        chain.doFilter(request, response);
        return;
        }
       
        PrintWriter writer=response.getWriter();
        response.setContentType("text/html");
        writer.print("intercepted\n");
       
  
        
        
        
      //  chain.doFilter(request, response);
        
     
    }

    @Override
    public void destroy() {
   
    }

   
   
   
  
   

   
    
    
}
