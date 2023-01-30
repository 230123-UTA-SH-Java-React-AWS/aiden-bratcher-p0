package com.revature;

import java.net.InetSocketAddress;

import com.revature.controllers.AnotherController;
import com.revature.controllers.Controller;
import com.revature.model.Employee;
import com.revature.repository.Repository;
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
        Employee employee = new Employee("bool","0964");

        repo.saveToFile(employee);

        server.createContext("/someURL", (HttpHandler) new Controller());
        server.createContext("/otherURL", (HttpHandler) new AnotherController());

        server.setExecutor(null);
        server.start();
    }
}
