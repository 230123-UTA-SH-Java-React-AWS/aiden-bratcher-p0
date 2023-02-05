package com.revature;

import java.net.InetSocketAddress;
import java.util.List;

import com.revature.controllers.LoginController;
import com.revature.controllers.ManagerTicketController;
import com.revature.controllers.TicketController;
import com.revature.controllers.Controller;
import com.revature.model.Employee;
import com.revature.repository.Repository;
import com.revature.utils.ConnectionUtils;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) throws Exception{
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        Repository repo = new Repository();
        // Employee employee = new Employee("ascii","11111");

        // repo.saveToDatabase(employee);

        server.createContext("/register", (HttpHandler) new Controller());
        server.createContext("/login", (HttpHandler) new LoginController());
        server.createContext("/tickets", (HttpHandler) new TicketController());
        server.createContext("/manageTickets", (HttpHandler) new ManagerTicketController());

        server.setExecutor(null);
        server.start();
    }
}
