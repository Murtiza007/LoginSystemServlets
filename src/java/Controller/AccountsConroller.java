
package Controller;

import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.users;


public class AccountsConroller {
    
   
    
    public void login(users user) throws IOException{
     
        if(isValid(user))
        {
           if(isAuthentic(user))
           {
               authorize(user);
               user.setMessage("welcome");
           }
           else{
               user.setMessage("Incorrect Credentials");
           }
        }
        else{
            user.setMessage("Please enter all fields");
        }
        
    
    }
    
    
    public boolean isValid(users user){
    
        return (!user.getUsername().equals("")&&
                !user.getPassword().equals(""));
    
    }
    public boolean isAuthentic(users user){
        return (user.getUsername().equals("moin")&&
                user.getPassword().equals("123"));
    
    }
    public void authorize(users user) throws IOException{
       
        HttpSession session=user.getReq().getSession();
        session.setAttribute("AuthorizationSession", user.getUsername());
        user.getRes().sendRedirect("profile");
    }
    public void isAuthorised(users user) throws IOException{
    HttpSession session=user.getReq().getSession();
        if(session.getAttribute("AuthorizationSession")==null
                || session.getAttribute("AuthorizationSession").equals("")){
        user.getRes().sendRedirect("home?ID=101");
        
        }
    
    }
    
    public static String getMessage(String parameter){
        String returnValue="";
       
        if (parameter.equals("101")){
        returnValue=" you are not authorized ";
        }
        else if (parameter.equals("102")){
         returnValue=" Logged out Sucessfully ";
        }
        
      
        return returnValue;
    }
    
    
    
}
