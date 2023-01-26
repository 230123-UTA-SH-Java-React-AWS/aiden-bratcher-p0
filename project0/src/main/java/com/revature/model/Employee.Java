package com.revature.model;

public class Employee {
    private String username;
    private String password;

    public Employee(String username, String password){
        this.password = password;
        this.username = username;
    }

    public Employee(){
        password = "junkPassword";
        username = "junkUsername";
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
}