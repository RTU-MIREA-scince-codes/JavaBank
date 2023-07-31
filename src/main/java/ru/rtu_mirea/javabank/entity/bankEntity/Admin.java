package ru.rtu_mirea.javabank.entity.bankEntity;

import jakarta.persistence.*;
import lombok.Data;
import ru.rtu_mirea.javabank.entity.systemEntities.User;

@Entity
@Table(name = "admin_t")
@Data
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "admin_number", nullable = false, unique = true)
    private String adminNumber;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}