package ru.rtu_mirea.javabank.entity.bankEntity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "debit_card_t")
@Data
public class DebitCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_number", nullable = false, unique = true)
    private String cardNumber;

    @Column(name = "cvv", nullable = false)
    private String cvv;

    @Column(name = "expiration_date", nullable = false)
    private Date expirationDate;

    @Column(name = "balance", nullable = false)
    private double balance;

    @Column(name = "active", nullable = false)
    private boolean active;
}
