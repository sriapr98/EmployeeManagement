package com.example.srikkanth.employeemanagement;

import java.io.Serializable;

public class EmployeeRecord implements Serializable {
    private String id;
    private String FirstName;
    private String LastName;
    private String Gender;
    private String Age;
    private String Email;
    private String Contact;
    public EmployeeRecord(){

    }
    public EmployeeRecord(String id,String FirstName, String LastName, String Gender,String Age, String Email,String Contact) {
        this.id=id;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Gender = Gender;
        this.Age = Age;
        this.Email = Email;
        this.Contact=Contact;
    }

    public String getId() {return id;}

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getGender() {
        return Gender;
    }

    public String getAge() {
        return Age;
    }

    public String getEmail() {
        return Email;
    }

    public String getContact() {return Contact;}
}
