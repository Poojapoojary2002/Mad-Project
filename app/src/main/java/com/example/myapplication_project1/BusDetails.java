package com.example.myapplication_project1;

public class BusDetails {
    String text_no;
    String text_driver;
    String text_route;
    String text_phone;

    public BusDetails(String text_no, String text_driver, String text_route,String text_phone) {
        this.text_no = text_no;
        this.text_driver = text_driver;
        this.text_route = text_route;
        this.text_phone = text_phone;
    }

    public String getTextno() {
        return text_no;
    }

    public String getTextdriver() {
        return text_driver;
    }

    public String getTextroute() {
        return text_route;
    }

    public String getTextphone() {
        return text_phone;
    }
}
