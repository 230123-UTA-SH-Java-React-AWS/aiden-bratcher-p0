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
    private Employee convertFromString(String jsonString){
        ObjectMapper mapper = new ObjectMapper();

        try {
            Employee newEmployee = mapper.readValue(jsonString, Employee.class);
            return newEmployee;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public String saveNewUser(String jsonString){
        //from string to object
        Repository repo = new Repository();

        String result = "";

        Employee newEmployee = convertFromString(jsonString);
        result = repo.saveToDatabase(newEmployee);

        return result;        
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

    public String saveNewTicket(String jsonString) {
        Repository repo = new Repository();
        String response = "";

        ObjectMapper mapper = new ObjectMapper();

        
        try {
            Ticket newTicket = mapper.readValue(jsonString, Ticket.class);

            response = repo.saveToDatabase(newTicket);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    public boolean checkForUser(String jsonString) {
        Repository repo = new Repository();

        boolean result = false;

        Employee newEmployee = convertFromString(jsonString);
        result = repo.checkIfExists(newEmployee);

        return result;
    }

    public boolean verifyPassword(String jsonString) {
        Repository repo = new Repository();

        boolean result = false;

        Employee newEmployee = convertFromString(jsonString);
        result = repo.verifyPassword(newEmployee);

        return result;
    }
}
