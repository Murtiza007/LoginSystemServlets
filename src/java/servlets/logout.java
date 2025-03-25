
package servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import model.users;

@WebServlet(urlPatterns="/logout")
public class logout extends HttpServlet {
    
    public void doGet(HttpServletRequest req ,HttpServletResponse res) throws IOException{
    
   HttpSession session=req.getSession();
   Cookie[] cookie=req.getCookies();
    
    if(session.getAttribute("AuthorizationSession")!=null){
        session.invalidate();
            
    }
    if(cookie!=null){
    
     for (Cookie ck :cookie){
                if(ck.getName().equals("AUthCookie")){
                    ck.setMaxAge(0);
                    res.addCookie(ck);
                }
               
    }
      res.sendRedirect("home?ID=102");
     
   
    
    
    
    
   
        
    
}
    }}
