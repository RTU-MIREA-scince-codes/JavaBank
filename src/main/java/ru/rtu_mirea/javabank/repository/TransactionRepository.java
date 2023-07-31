package ru.rtu_mirea.javabank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rtu_mirea.javabank.entity.bankEntity.Client;
import ru.rtu_mirea.javabank.entity.bankEntity.Transaction;

import java.util.Collection;
import java.util.Date;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findByTransactionNumber(String transactionNumber);
    Transaction findByTransactionDate(Date transactionDate);
    Collection<Transaction> findAllByTransactionDate(Date transactionDate);
    Collection<Transaction> findAllByTransactionAmount(double transactionAmount);
}
