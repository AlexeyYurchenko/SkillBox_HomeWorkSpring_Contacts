package org.example;

import org.example.model.Person;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class DevDatasource implements DatasourceConfig{
    private List<Person> people;

    @Value("${app.env}")
    private String evnProfiles;

    @PostConstruct
    @Override
    public void setup() {
        evnProfiles = "=> Hi I'm developer <=";
        System.out.println(evnProfiles);
        people= new ArrayList<>();
        people.add(new Person("Иванов Иван Иванович","+79138205145","Test@mail.ru"));
    }

    @Override
    public void save(Person person) {
        people.add(person);
    }

    @Override
    public void delete(String email) {
        people.removeIf(p -> Objects.equals(p.getEmail(), email));
    }

    @Override
    public void list() {
        people.forEach(System.out::println);
    }

    @Override
    public void saveDB() {

    }
}