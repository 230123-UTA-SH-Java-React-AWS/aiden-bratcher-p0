package com.revature;

import com.revature.model.Employee;
import com.revature.repository.Repository;

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
    public static void main(String[] args) {
        Repository repo = new Repository();
        Employee employee = new Employee("booleons","0964");

        repo.saveToFile(employee);
    }
}
