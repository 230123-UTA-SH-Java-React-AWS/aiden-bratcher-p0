package com.revature.controllers;
import java.io.IOException;
import java.io.OutputStream;

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
        String resultResponse = "";

        


        exchange.sendResponseHeaders(200, resultResponse.getBytes().length);

        OutputStream os = exchange.getResponseBody();
        os.write(resultResponse.getBytes());
        os.close();
    }
    
    
}