package com.dharaneesh.rest.webservices.restful_web_services.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "USER_DETAILS")
public class User {

    protected User() {}

    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message = "Name is Required")
//    @JsonProperty("user_name")
    @Column(name = "NAME")
    private String name;

    @Past(message = "BirthDate Should be Always be Past")
//    @JsonProperty("birth_date")
    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User(int id, String name, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
