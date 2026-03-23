package com.portfolio.ebookshop.models;

import jakarta.persistence.*;

/*
users table:
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    address VARCHAR(255)
*/

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 255)
    private String username;
    private String email;
    private String password;
    private String address;

    public User(){}

    //Getters and Setters
    public int getId(){return id;}
    public void setId(int id){this.id = id;}

    public String getUsername(){return username;}
    public void setUsername(String username){this.username = username;}

    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}

    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}

    public String getAddress(){return address;}
    public void setAddress(String address){this.address = address;}
}