
package model;

public class users extends servletEssentials {
    
    private String Username;
    private String Password;
    private String rememberme;
    private String email;
    private String Phone;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }
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
