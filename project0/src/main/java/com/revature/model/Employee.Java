package com.revature.model;

public class Employee {
    private String email;
    private String password;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int iD) {
        id = iD;
    }

    public Employee(String email, String password){
        this.password = password;
        this.email = email;
    }

    public Employee(){
        password = "junkPassword";
        email = "junkUsername";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}