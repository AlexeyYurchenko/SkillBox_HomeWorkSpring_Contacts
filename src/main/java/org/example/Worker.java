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
        System.out.println("Выбор команды: " +
                "(save) Сохранить контакт, " +
                "(delete) удалить контакт по email, " +
                "(list) список контактов, " +
                "(exit) выход");

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        input = bf.readLine();
        while (!input.equals("exit")) {
            switch (input) {
                case "save" -> {
                    System.out.println("Введите Ф.И.О: ");
                    String fullName = bf.readLine().trim();
                    if (!PersonValidator.isName(fullName)){
                        System.out.println("Неверный формат ввода ФИО");
                        continue;
                    }
                    System.out.println("Введите телефон: ");
                    String phoneNumber = bf.readLine().trim();
                    if (!PersonValidator.isNumber(phoneNumber)){
                        System.out.println("Неверный формат ввода телефона");
                        continue;
                    }
                    System.out.println("Введите email: ");
                    String email = bf.readLine().trim();
                    if (!PersonValidator.isEmail(email)){
                        System.out.println("Неверный формат ввода email");
                        continue;
                    }
                    datasourceConfig.save(new Person(fullName, phoneNumber, email));

                }
                case "delete" -> {
                    System.out.println("Введите email контакта который нужно удалить: ");
                    String email = bf.readLine().trim();
                    datasourceConfig.delete(email);
                }
                case "list" -> datasourceConfig.list();
            }
            System.out.println("Выбор команды: (save) Сохранить контакт, (delete) удалить контакт по email, (list) список контактов, (exit) выход");
            input = bf.readLine();
        }
        bf.close();
        datasourceConfig.saveDB();
    }
}