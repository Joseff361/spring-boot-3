package com.luv2code.cruddemo.entity;

import jakarta.persistence.*;

// This tells JPA that instances of this class can be stored in a database.
@Entity
// This allows you to customize the mapping between your entity class and the corresponding database table.
@Table(name = "student")
public class Student {

    @Id
    //This strategy relies on the auto-increment functionality provided by the database to generate unique identifier
    // values automatically.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    // An empty constructor is needed to create a new instance via reflection by your persistence framework. If you
    // don't provide any additional constructors with arguments for the class, you don't need to provide an empty
    // constructor because you get one per default.
    public Student() {
    }

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            '}';
    }
}
