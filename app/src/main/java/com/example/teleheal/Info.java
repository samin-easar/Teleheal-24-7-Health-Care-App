package com.example.teleheal;

public class Info {

    String name;
    String price;
    String details;
    String dname,contact,chember,designation,fee;

    public Info(String dname, String contact, String chember, String designation, String fee) {
        this.dname = dname;
        this.contact = contact;
        this.chember = chember;
        this.designation = designation;
        this.fee = fee;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getChember() {
        return chember;
    }

    public void setChember(String chember) {
        this.chember = chember;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

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
