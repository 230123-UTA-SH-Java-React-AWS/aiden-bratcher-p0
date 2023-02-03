package com.revature.controllers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.revature.repository.Repository;
import com.revature.service.Service;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class LoginController implements HttpHandler{

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String httpVerb = exchange.getRequestMethod();

        switch (httpVerb){
            case "POST":
                postRequest(exchange);
                break;

            case "GET":
                getRequest(exchange);
                break;
            case "PUT":
                putRequest(exchange);
                break;
            default:
                //implementation details if accessing http verb not supported
                String someResponse = "HTTP Verb not supported";

                exchange.sendResponseHeaders(404, someResponse.getBytes().length);

                OutputStream os = exchange.getResponseBody();
                os.write(someResponse.getBytes());
                os.close();
                break;
        }     
    }

    public void putRequest(HttpExchange exchange) {

    }

    public void getRequest(HttpExchange exchange) {
    }

    public void postRequest(HttpExchange exchange) throws IOException {
        InputStream is = exchange.getRequestBody();

        StringBuilder textBuilder = new StringBuilder();
        try(Reader reader = new BufferedReader(new InputStreamReader(is, Charset.forName(StandardCharsets.UTF_8.name())))){
            int c = 0;

            //read() will return -1 when there are no more characters
            while ((c = reader.read()) != -1){
                textBuilder.append((char)c);
            }
        }

        String resultResponse = "Login Unsuccessful. Make sure you entered the correct email and password";

        Service serv = new Service();
        if (serv.checkForUser(textBuilder.toString())){
            if (serv.verifyPassword(textBuilder.toString())){
                resultResponse = "Login Successful";
            }
        }


        exchange.sendResponseHeaders(200, resultResponse.getBytes().length);

        OutputStream os = exchange.getResponseBody();
        os.write(resultResponse.getBytes());
        os.close();
    }
    
    
}