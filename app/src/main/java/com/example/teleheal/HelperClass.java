package com.example.teleheal;

public class HelperClass {
    String email,fullname,username,password;



    //Today
    public static String stringToPass = " ";

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HelperClass(String email,String fullname, String username, String password) {
        this.email = email;
        this.fullname=fullname;
        this.username = username;
        this.password = password;
    }

    public HelperClass() {
    }
}
