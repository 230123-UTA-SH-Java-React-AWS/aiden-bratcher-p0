package com.revature.service;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.revature.model.Employee;
import com.revature.model.Ticket;
import com.revature.repository.Repository;

//Behavior driven classes
public class Service {
    public void saveFromFile(String jsonString){
        //from string to object
        Repository repo = new Repository();

        ObjectMapper mapper = new ObjectMapper();

        
        try {
            Employee newEmployee = mapper.readValue(jsonString, Employee.class);

            repo.saveToDatabase(newEmployee);
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String getAllUsers(){
        Repository repo = new Repository();
        List<Employee> listOfUsers = repo.getAllUsers();

        ObjectMapper map = new ObjectMapper();

        String jsonString = "";

        try{
            jsonString = map.writeValueAsString(listOfUsers);
        } catch (JsonGenerationException e){
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return jsonString;
    }

    public String getAllTickets(){
        Repository repo = new Repository();
        List<Ticket> listOfTickets = repo.getAllTickets();

        ObjectMapper map = new ObjectMapper();

        String jsonString = "";

        try {
            jsonString = map.writeValueAsString(listOfTickets);
        } catch (JsonGenerationException e){
            e.printStackTrace();
        } catch (JsonMappingException e){
            e.printStackTrace();  
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return jsonString;
    }

    public void saveNewTicket(String jsonString) {
        Repository repo = new Repository();

        ObjectMapper mapper = new ObjectMapper();

        
        try {
            Ticket newTicket = mapper.readValue(jsonString, Ticket.class);

            repo.saveToDatabase(newTicket);
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
