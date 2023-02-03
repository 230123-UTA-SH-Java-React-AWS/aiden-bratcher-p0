package com.revature.repository;
import com.revature.model.Employee;
import com.revature.model.Ticket;
import com.revature.utils.ConnectionUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.postgresql.util.PSQLException;

//Responsible for interacting with a database and sending/recieving information from the database
public class Repository {
    public String saveToDatabase(Employee employee){
        String result = "";

        String sql = "insert into users (username, password, ismanager) values(?, ?, ?)";

        if (!checkIfExists(employee)){
            try (Connection con = ConnectionUtils.getConnection()) {
                PreparedStatement prstmt = con.prepareStatement(sql);

                prstmt.setString(1, employee.getEmail());
                prstmt.setString(2, employee.getPassword());
                prstmt.setBoolean(3, false);

                prstmt.execute();
                result = "Registration Successful";
                return result;
            }  catch (Exception e){
                e.printStackTrace();
            }
        } else result = "That email is already in use";

        return result;
    }


    public List<Employee> getAllUsers() {
        String sql = "select * from users";
        List<Employee> listOfUsers = new ArrayList<Employee>();

        try (Connection con = ConnectionUtils.getConnection()) {
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                Employee newEmployee = new Employee();

                newEmployee.setId(rs.getInt("id"));
                newEmployee.setEmail(rs.getString("username"));
                newEmployee.setPassword(rs.getString("password"));

                listOfUsers.add(newEmployee);
            }
        } catch (Exception e) {

        }
        return listOfUsers;
    }


    public List<Ticket> getAllTickets() {
        String sql = "select * from ticket";
        List<Ticket> listOfUsers = new ArrayList<Ticket>();

        try (Connection con = ConnectionUtils.getConnection()) {
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                Ticket newTicket = new Ticket();

                newTicket.setId(rs.getInt("id"));
                newTicket.setEmpId(rs.getInt("personid"));
                newTicket.setAmount(rs.getInt("amount"));
                newTicket.setDescription(rs.getString("description"));
                newTicket.setStatus(rs.getString("status"));

                listOfUsers.add(newTicket);
            }
        } catch (Exception e) {

        }
        return listOfUsers;
    }

    public String saveToDatabase(Ticket ticket) {
        String response = "";
        String sql = "insert into ticket (personid, amount, description, status) values(?, ?, ?, ?)";

        if (ticket.getAmount() < 0 || ticket.getDescription() == null){
            response = "Please enter a description and valid amount";
        } else {
            try (Connection con = ConnectionUtils.getConnection()) {
                PreparedStatement prstmt = con.prepareStatement(sql);

                prstmt.setInt(1, 1);
                prstmt.setInt(2, ticket.getAmount());
                prstmt.setString(3, ticket.getDescription());
                prstmt.setString(4, ticket.getStatus());

                prstmt.execute();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        return response;
    }

    public boolean checkIfExists(Employee employee){
        boolean result = false;
        String sql = "select count(username) as total from users where username = ?";

        try (Connection con = ConnectionUtils.getConnection()) {
            PreparedStatement prstmt = con.prepareStatement(sql);

            prstmt.setString(1, employee.getEmail());
            ResultSet rs = prstmt.executeQuery();

            while(rs.next()){
                if(rs.getInt("total") != 0){
                    result = true;
                }
            }            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean verifyPassword(Employee employee) {
        boolean result = false;
        String sql = "select count(password) as total from users where username = ? and password = ?";

        try (Connection con = ConnectionUtils.getConnection()) {
            PreparedStatement prstmt = con.prepareStatement(sql);

            prstmt.setString(1, employee.getEmail());
            prstmt.setString(2, employee.getPassword());
            ResultSet rs = prstmt.executeQuery();

            while(rs.next()){
                if(rs.getInt("total") != 0){
                    result = true;
                }
            }            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    
}
