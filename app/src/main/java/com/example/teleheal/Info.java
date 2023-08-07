package com.example.teleheal;

public class Info {

    String name;
    String price;
    String details;

    public Info() {
    }

    public Info(String name, String price,String details) {
        this.name = name;
        this.price = price;
        this.details=details;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
