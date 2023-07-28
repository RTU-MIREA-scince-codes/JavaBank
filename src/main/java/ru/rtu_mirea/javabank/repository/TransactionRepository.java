package ru.rtu_mirea.javabank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rtu_mirea.javabank.entity.bankEntity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findByTransactionNumber(String transactionNumber);
    Transaction findByTransactionDate(String transactionDate);
}
