package ru.rtu_mirea.javabank.entity.bankEntity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bank_account_t")
@Data
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;

    @Column(name = "balance", nullable = false)
    private double balance;

    @Column(name = "active", nullable = false)
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany
    @JoinTable(name = "bank_account_debit_card_t",
            joinColumns = @JoinColumn(name = "bank_account_id"),
            inverseJoinColumns = @JoinColumn(name = "debit_card_id"))
    private Set<DebitCard> debitCards = new HashSet<>();
}
