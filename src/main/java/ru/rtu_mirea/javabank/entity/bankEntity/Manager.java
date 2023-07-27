package ru.rtu_mirea.javabank.entity.bankEntity;

import jakarta.persistence.*;
import lombok.Data;
import ru.rtu_mirea.javabank.entity.systemEntities.User;

@Entity
@Table(name = "manager_t")
@Data
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "manager_number", nullable = false, unique = true)
    private String managerNumber;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
