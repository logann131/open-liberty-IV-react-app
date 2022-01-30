package com.app.rest.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data // Setters, Getters, ToString
@AllArgsConstructor // constructor with all argument 
@NoArgsConstructor // default constructor with no args
public class User {
    
    @Id // primary key
    @GeneratedValue // auto generated and incremented
    private Long ID;    

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
