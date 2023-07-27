package ru.rtu_mirea.javabank.entity.systemEntities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "group_t")
@Data
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", unique = true, nullable = false)
    private String code;
    @Column(name = "description_of_group", nullable = false)
    private String description;

    @ManyToMany(mappedBy = "userGroups")
    private Set<User> users;
}
