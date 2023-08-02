package ru.rtu_mirea.javabank.dto;

import lombok.Data;

@Data
public class TransactionDTO {
    private Long bankAccountId;
    private Long bankAccountToId;
    private double amount;
}
