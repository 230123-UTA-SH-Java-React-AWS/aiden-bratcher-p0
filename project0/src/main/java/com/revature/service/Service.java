package com.revature.service;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.revature.model.Employee;
import com.revature.repository.Repository;

//Behavior driven classes
public class Service {
    public void saveFromFile(String jsonString){
        //from string to object
        Repository repo = new Repository();

        ObjectMapper mapper = new ObjectMapper();

        
        try {
            Employee newEmployee = mapper.readValue(jsonString, Employee.class);

            repo.saveToFile(newEmployee);
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
