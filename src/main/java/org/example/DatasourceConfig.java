package org.example;

import org.example.model.Person;

public interface DatasourceConfig {
    public void setup() ;
    public void save(Person person);
    public void delete(String email);
    public void list();
    public void saveDB();
}

