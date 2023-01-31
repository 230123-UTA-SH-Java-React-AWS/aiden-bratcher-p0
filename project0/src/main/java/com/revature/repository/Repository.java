package com.revature.repository;
import com.revature.model.Employee;
import com.revature.utils.ConnectionUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

//Responsible for interacting with a database and sending/recieving information from the database
public class Repository {
    public void saveToDatabase(Employee employee){
        // ObjectMapper mapper = new ObjectMapper();
        // String jsonObject = "";

        

        // try{
        //     jsonObject = mapper.writeValueAsString(employee);

        //     File export = new File("employee.json");
        //     export.createNewFile();

        //     FileWriter writer = new FileWriter("employee.json");
        //     writer.write(jsonObject);
        //     writer.close();
        // } catch (JsonGenerationException e) {
        //     e.printStackTrace();
        // } catch (JsonMappingException e) {
        //     e.printStackTrace();
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }

        String sql = "insert into users (username, password) values(?, ?)";

        try (Connection con = ConnectionUtils.getConnection()) {
            PreparedStatement prstmt = con.prepareStatement(sql);

            prstmt.setString(1, employee.getUsername());
            prstmt.setString(2, employee.getPassword());

            prstmt.executeQuery();
        } catch (Exception e){
            e.printStackTrace();
        }


    }
}
