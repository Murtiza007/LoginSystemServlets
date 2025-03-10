
package servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns="/logout")
public class logout extends HttpServlet {
    
    public void doGet(HttpServletRequest req ,HttpServletResponse res) throws IOException{
    HttpSession session=req.getSession();
    if(session.getAttribute("AuthorizationSession")!=null){
        session.removeAttribute("AuthorizationSession");
    }
    res.sendRedirect("home?ID=102");
    } 
    
}
