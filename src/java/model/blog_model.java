
package model;


public class blog_model extends servletEssentials {
    
    private String blog_text;
    private String blog_username;
    private String blog_time;
    private String blog_log;

    public String getBlog_log() {
        return blog_log;
    }

    public void setBlog_log(String blog_log) {
        this.blog_log = blog_log;
    }

    public String getBlog_username() {
        return blog_username;
    }

    public void setBlog_username(String blog_username) {
        this.blog_username = blog_username;
    }

    public String getBlog_time() {
        return blog_time;
    }

    public void setBlog_time(String blog_time) {
        this.blog_time = blog_time;
    }

    public String getBlog_text() {
        return blog_text;
    }

    public void setBlog_text(String blog_text) {
        this.blog_text = blog_text;
    }
    
}

