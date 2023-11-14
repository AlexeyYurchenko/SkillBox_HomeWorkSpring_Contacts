package org.example;

import org.example.model.Person;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ProdDatasource implements DatasourceConfig {
    private List<Person> people;

    private final File file = new File("src/main/resources/default-contacts.txt");

    @Value("${app.env}")
    private String evnProfiles;

    @PostConstruct
    @Override
    public void setup() {
        evnProfiles = "=> Hi I'm production <=";
        System.out.println(evnProfiles);
        people = new ArrayList<>();
        Scanner input = null;
        try {
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        input.useDelimiter(";|\n");
        while (input.hasNextLine()) {
            String fullName = input.next();
            String phoneNumber = input.next();
            String email = input.next();
            people.add(new Person(fullName, phoneNumber, email));
        }
    }
    @PreDestroy
    public void saveDB() {
        System.out.println("Saving new contacts....");
        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(file));
            for (Person str : people) {
                try {
                    br.write(str.toString() + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            br.flush();
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void save(Person person) {
        people.add(person);
        System.out.println("Contact => " + person + " <= saved!");
    }

    @Override
    public void delete(String email) {
        if (people.removeIf(p -> Objects.equals(p.getEmail(), email))){
            System.out.println("Contact => " + email + " <= deleted!");
        } else {
            System.out.println("Contact with this => " + email + " <= not found!");
        }
    }

    @Override
    public void list() {
        System.out.println("==========All contacts=========");
        people.forEach(System.out::println);
    }
}