package com.example.myapplication_project1;

public class StudentDetails {
    String stu_busno,stu_phone;

    public StudentDetails(String stu_bus, String stu_phone) {
        this.stu_busno = stu_bus;
        this.stu_phone = stu_phone;
    }

    public String getStu_bus() {
        return stu_busno;
    }

    public String getStu_phone() {
        return stu_phone;
    }
}
