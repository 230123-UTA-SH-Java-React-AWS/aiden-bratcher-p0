package com.revature.controllers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.sun.net.httpserver.HttpHandler;
import com.revature.model.Employee;
import com.revature.service.Service;
import com.sun.net.httpserver.HttpExchange;

public class ManagerTicketController implements HttpHandler{
    
    @Override
    public void handle(HttpExchange exchange) throws IOException{
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

    //Get requests from /someURL
    public void getRequest(HttpExchange exchange) throws IOException{
        Service serv = new Service();
        String jsonCurrentList = serv.getAllTickets();

        exchange.sendResponseHeaders(200, jsonCurrentList.getBytes().length);

        OutputStream os = exchange.getResponseBody();
        os.write(jsonCurrentList.getBytes());
        os.close();
    }

    public void putRequest(HttpExchange exchange) throws IOException{
        String someResponse = "the put response";

        exchange.sendResponseHeaders(200, someResponse.getBytes().length);

        OutputStream os = exchange.getResponseBody();
        os.write(someResponse.getBytes());
        os.close();
    }

    public void postRequest(HttpExchange exchange) throws IOException {
    }
}
