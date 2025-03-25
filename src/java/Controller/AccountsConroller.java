
package Controller;

import jakarta.servlet.http.Cookie;
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
        Cookie cookie=new Cookie("AUthCookie",user.getUsername());
        
        if(user.getReq().getParameter("remember_me")!=null){
        cookie.setMaxAge(60*60*24);
        cookie.isHttpOnly();
        user.getRes().addCookie(cookie);
        
        
        }
        else{
        session.setAttribute("AuthorizationSession", user.getUsername());
        
        }
        user.getRes().sendRedirect("profile");
    }
    public void isAuthorised(users user) throws IOException{
    HttpSession session=user.getReq().getSession();
    Cookie[] cookie=user.getReq().getCookies();
    
    boolean isValid=false;
    
    
        if(user.getReq().getSession().getAttribute("AuthorizationSession")!=null
                ){
           isValid=true;
            
        }  
        
        else if(cookie!=null){
            for (Cookie ck :cookie){
                if(ck.getName().equals("AUthCookie")){
                    isValid=true;
                    break;
                }      
                    
             } 
         }     

          if(isValid==false){ 
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
