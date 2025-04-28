package html_essentials;


public class essentials {
   
    public String getHeader() {
    return "<html>"
            + "<head>"
            + "<title>My Blog</title>"
            
           // + "<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-ENjdO4Dr2bkBIFxQpeoQwX1HIj6YzFfN6tZ2fVZlHRQQ1sZ4IoRZB8VycN3ygc7F' crossorigin='anonymous'>"
            + "<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7' crossorigin='anonymous'>"
            + "<link rel='stylesheet' href='css/style.css'/>"
            
            + "<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js' integrity='sha384-qkL14X1U+MIWauB1v1NjKQf1zToF5LoVfpxk+6kWOM+VRR8Lz4YUGBHyD1PgZj/e' crossorigin='anonymous'></script>"
            + "</head>"
            + "<body class='bg-white text-black'>";
}
    
  
   
 public String getNavigation() {
    return "<nav class=\"navbar navbar-expand-lg navbar-dark bg-primary\">"
            + "<div class=\"container-fluid\">"
            + "<a class='navbar-brand fs-1 ms-4' href=\"#\">WordNest</a>"
            + "<button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarNavAltMarkup\" aria-controls=\"navbarNavAltMarkup\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">"
            + "<span class=\"navbar-toggler-icon\"></span>"
            + "</button>"
            + "<div class=\"collapse navbar-collapse\" id=\"navbarNavAltMarkup\">"
            + "<div class='navbar-nav  ms-auto me-3'>"
            + "<a class=\"nav-link active fs-5\" aria-current=\"page\" href=\"#\">Home</a>"
            + "<a class=\"nav-link active fs-5\" href=\"#\">Features</a>"
            + "<a class=\"nav-link active fs-5 me-2\" href=\"#\">Pricing</a>"
            
          +"<a href='logout' class='btn btn-danger text-white fs-5' >Logout</a>"
            


            
            + "</div>"
            + "</div>"
            + "</div>"
            + "</nav>";
}


    public String getFooter(){
    return  
            "</body >"+""
            + "</html>";
        
    }
    
}
