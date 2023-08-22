package ru.rtu_mirea.javabank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rtu_mirea.javabank.entity.bankEntity.Client;
import ru.rtu_mirea.javabank.entity.bankEntity.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Manager findByManagerNumber(String managerNumber);
}
