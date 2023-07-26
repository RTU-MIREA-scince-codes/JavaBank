package ru.rtu_mirea.javabank.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "client_t")
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "client_number", nullable = false, unique = true)
    private String clientNumber;

    @Column(name = "passport_number", nullable = false, unique = true)
    private String passportNumber;

    @OneToMany
    @JoinTable(name = "client_bank_account_t",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "bank_account_id"))
    private Set<BankAccount> bankAccounts = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
