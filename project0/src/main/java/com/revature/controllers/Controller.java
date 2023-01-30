package com.revature.controllers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.sun.net.httpserver.HttpHandler;
import com.revature.service.Service;
import com.sun.net.httpserver.HttpExchange;

public class Controller implements HttpHandler{
    
    @Override
    public void handle(HttpExchange exchange) throws IOException{
        // String someResponse = "response";

        // exchange.sendResponseHeaders(200, someResponse.getBytes().length);

        // OutputStream os = exchange.getResponseBody();
        // os.write(someResponse.getBytes());
        // os.close();

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
        String someResponse = "the get response";

        exchange.sendResponseHeaders(200, someResponse.getBytes().length);

        OutputStream os = exchange.getResponseBody();
        os.write(someResponse.getBytes());
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
        InputStream is = exchange.getRequestBody();

        //Convert InputStream to String
        StringBuilder textBuilder = new StringBuilder();
        try(Reader reader = new BufferedReader(new InputStreamReader(is, Charset.forName(StandardCharsets.UTF_8.name())))){
            int c = 0;

            //read() will return -1 when there are no more characters
            while ((c = reader.read()) != -1){
                textBuilder.append((char)c);
            }
        }

        System.out.println(textBuilder.toString());

        exchange.sendResponseHeaders(200, textBuilder.toString().getBytes().length);

        //make static later?
        Service serv = new Service();
        serv.saveFromFile(textBuilder.toString());

        OutputStream os = exchange.getResponseBody();
        os.write(textBuilder.toString().getBytes());
        os.close();
    }
}
