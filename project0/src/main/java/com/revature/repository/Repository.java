package com.revature.repository;
import com.revature.model.Employee;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

//Responsible for interacting with a database and sending/recieving information from the database
public class Repository {
    public void saveToFile(Employee employee){
        ObjectMapper mapper = new ObjectMapper();
        String jsonObject = "";

        

        try{
            jsonObject = mapper.writeValueAsString(employee);

            File export = new File("employee.json");
            export.createNewFile();

            FileWriter writer = new FileWriter("employee.json");
            writer.write(jsonObject);
            writer.close();
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
