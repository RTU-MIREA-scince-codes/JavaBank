package ru.rtu_mirea.javabank.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rtu_mirea.javabank.entity.bankEntity.BankAccount;
import ru.rtu_mirea.javabank.entity.bankEntity.Client;
import ru.rtu_mirea.javabank.entity.bankEntity.Transaction;
import ru.rtu_mirea.javabank.repository.BankAccountRepository;
import ru.rtu_mirea.javabank.repository.ClientRepository;
import ru.rtu_mirea.javabank.repository.TransactionRepository;

import java.util.Collection;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {
    private final ClientRepository clientRepository;
    private final TransactionRepository transactionRepository;
    private final BankAccountRepository bankAccountRepository;

    // Methods for classic client operations in bank
    public Set<BankAccount> getBankAccounts(String clientNumber) {
        try {
            Client client = clientRepository.findByClientNumber(clientNumber);
            return client.getBankAccounts();
        } catch (Exception e) {
            log.error("Error in ClientService.getBankAccounts: " + e.getMessage());
            return null;
        }
    }
    public void createBankAccount(String clientNumber) {
        Client client = clientRepository.findByClientNumber(clientNumber);
        BankAccount bankAccount = new BankAccount();
        bankAccount.setClient(client);
        bankAccount.setAccountNumber("B_" + client.getClientNumber() + "_" + (client.getBankAccounts().size() + 1));
        bankAccount.setBalance(0);
        bankAccount.setActive(true);
        client.getBankAccounts().add(bankAccount);

        clientRepository.save(client);
        bankAccountRepository.save(bankAccount);
        log.info("Bank account created by client: " + clientNumber);
    }

    public void transactionToBankAccount(Long bankAccountNumber, Long bankAccountNumberTo, double amount) {
        BankAccount bankAccount = bankAccountRepository.findById(bankAccountNumber).orElseThrow();
        BankAccount bankAccountTo = bankAccountRepository.findById(bankAccountNumberTo).orElseThrow();

        if (bankAccount.withdrawFundsFromBankAccount(amount)) {
            bankAccountTo.addingFundsToBankAccount(amount);
            Transaction transaction = new Transaction();
            transaction.setBankAccount(bankAccount);
            transaction.setBankAccountTo(bankAccountTo);
            transaction.setTransactionAmount(amount);
            transaction.setClient(bankAccount.getClient());
            transaction.setManager(bankAccount.getClient().getManager());
            transaction.setTransactionDate(new java.util.Date());
            transaction.setTransactionNumber("T_" + transaction.getTransactionDate().toString().replace(" ", "_"));

            transactionRepository.save(transaction);
            bankAccountRepository.save(bankAccount);
            bankAccountRepository.save(bankAccountTo);
            log.info("Transaction completed");
        } else {
            log.error("Error in ClientService.transactionToBankAccount: Not enough money");
        }
    }

    // Show client's transactions
    public Set<Transaction> getTransactions(String clientNumber) {
        try {
            Client client = clientRepository.findByClientNumber(clientNumber);
            // Collection<Transaction> transactions = TransactionRepository.findAllByClientOrderByOrderByTransactionDateAsc(client.getId());
            return null;
        } catch (Exception e) {
            log.error("Error in ClientService.getTransactions: " + e.getMessage());
            return null;
        }
    }
}
