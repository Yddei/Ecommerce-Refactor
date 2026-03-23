package com.portfolio.ebookshop.dto;
import jakarta.validation.constraints.*;

public class RegistrationRequest {

    @NotBlank(message = "Field is required")
    private String username;
    @Email(message = "Invalid email format")
    private String email;
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
    @NotBlank(message = "Field is required")
    private String address;


    public String getUsername(){return username;}
    public void setUsername(String username){this.username = username;}

    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}

    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}

    public String getAddress(){return address;}
    public void setAddress(String address){this.address = address;}
}
