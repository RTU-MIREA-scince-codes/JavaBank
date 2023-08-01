package ru.rtu_mirea.javabank.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String telephoneNumber;
    private String password;
}
