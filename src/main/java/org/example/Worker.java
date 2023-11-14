package org.example;

import org.example.model.Person;
import org.example.util.PersonValidator;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class Worker {
    private final DatasourceConfig datasourceConfig;

    public Worker(DatasourceConfig datasourceConfig) {
        this.datasourceConfig = datasourceConfig;
    }

    public void doWork() throws IOException {
        String input;
        String selectCommand = "Select command: " +
                "(save) Save contact, " +
                "(delete) delete contact by email, " +
                "(list) contact list, " +
                "(exit) exit";
        System.out.println(selectCommand);

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        input = bf.readLine();
        while (!input.equals("exit")) {
            switch (input) {
                case "save" -> {
                    System.out.println("enter your full name: ");
                    String fullName = bf.readLine().trim();
                    if (!PersonValidator.isName(fullName)) {
                        System.out.println("Invalid full name input format");
                        continue;
                    }
                    System.out.println("enter your telephone number: ");
                    String phoneNumber = bf.readLine().trim();
                    if (!PersonValidator.isNumber(phoneNumber)) {
                        System.out.println("Invalid telephone number entry format");
                        continue;
                    }
                    System.out.println("enter your email: ");
                    String email = bf.readLine().trim();
                    if (!PersonValidator.isEmail(email)) {
                        System.out.println("Invalid email input format");
                        continue;
                    }
                    datasourceConfig.save(new Person(fullName, phoneNumber, email));

                }
                case "delete" -> {
                    System.out.println("Enter the email of the contact you want to delete: ");
                    String email = bf.readLine().trim();
                    datasourceConfig.delete(email);
                }
                case "list" -> datasourceConfig.list();
            }
            System.out.println(selectCommand);
            input = bf.readLine();
        }
        bf.close();
        datasourceConfig.saveDB();
    }
}