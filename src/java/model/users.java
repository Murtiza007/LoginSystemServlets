
package model;

public class users extends servletEssentials {
    
    private String Username;
    private String Password;
    private String rememberme;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    private String message;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    
   
     public String getRememberme() {
        return rememberme;
    }

    public void setRememberme(String remember) {
        this.rememberme = remember;
    }
}
