package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity {

    private long id;

    @Column(name = "username")
    @NotNull(message = "username must not be null")
    @NotEmpty(message = "username must not be empty")
    private String username;

    @Column(name = "password")
    @NotNull(message = "password must not be null")
    @NotEmpty(message = "password must not be empty")
    private String password;

    @Column(name = "firstName")
    @NotNull(message = "first name must not be null")
    @NotEmpty(message = "first name must not be empty")
    private String firstName;

    @Column(name = "lastName")
    @NotNull(message = "last name must not be null")
    @NotEmpty(message = "last name must not be empty")
    private String lastName;

    @Column(name = "birthDate")
    @NotNull(message = "birth date must not be null")
    private Date birthDate;
}
