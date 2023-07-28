package ru.rtu_mirea.javabank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rtu_mirea.javabank.entity.bankEntity.DebitCard;

@Repository
public interface DebitCardRepository extends JpaRepository<DebitCard, Long> {
    DebitCard findByCardNumber(String cardNumber);
}
