package ru.rtu_mirea.javabank.dto;

import lombok.Data;

@Data
public class ClientDTO {
    private String email;
    private String passportNumber;
    private String managerNumber;
}
