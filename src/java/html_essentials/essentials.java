package html_essentials;


public class essentials {
   
    public String getHeader(){
    return "<html>"
            + "<head>"
                +"<title>"
                +"</title>"
            + "</head>"
            +"<link rel='stylesheet' href='css/style.css'/>"
            + "<body>";
        
    }
    public String getFooter(){
    return "</body>"+""
            + "</html>";
        
    }
    
}
