package ru.rtu_mirea.javabank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rtu_mirea.javabank.entity.bankEntity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByClientNumber(String clientNumber);
    Client findByPassportNumber(String passportNumber);
}
