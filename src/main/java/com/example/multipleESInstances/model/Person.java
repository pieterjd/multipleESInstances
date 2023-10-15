package com.example.multipleESInstances.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Document(indexName = "person")
public class Person {
    public Person() {
    }

    public Person(String id, String fullname) {
        this.id = id;
        this.fullname = fullname;
    }

    @Id
    String id;
    @Field
    private String fullname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                '}';
    }
}
