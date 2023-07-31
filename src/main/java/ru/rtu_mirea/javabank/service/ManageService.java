package ru.rtu_mirea.javabank.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rtu_mirea.javabank.entity.bankEntity.BankAccount;
import ru.rtu_mirea.javabank.entity.bankEntity.Client;
import ru.rtu_mirea.javabank.entity.bankEntity.Transaction;
import ru.rtu_mirea.javabank.repository.ClientRepository;
import ru.rtu_mirea.javabank.repository.TransactionRepository;

import java.util.Collection;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManageService {
    private ClientRepository clientRepository;
    private TransactionRepository transactionRepository;

    public Set<BankAccount> showClientBankAccounts(String clientNumber) {
        try {
            Set<BankAccount> clientsBankAccounts = clientRepository.findByClientNumber(clientNumber).getBankAccounts();
            return clientsBankAccounts;
        } catch (Exception e) {
            log.error("Error in ManageService.showClientBankAccounts: " + e.getMessage());
            return null;
        }
    }

    public BankAccount showClientBankAccount(String clientNumber, String bankAccountNumber) {
        try {
            BankAccount bankAccount = clientRepository.findByClientNumber(clientNumber).getBankAccounts().stream()
                    .filter(bankAccount1 -> bankAccount1.getAccountNumber().equals(bankAccountNumber))
                    .findFirst().orElseThrow();
            return bankAccount;
        } catch (Exception e) {
            log.error("Error in ManageService.showClientBankAccount: " + e.getMessage());
            return null;
        }
    }

    public Collection<Transaction> showClientTransactions(String clientNumber) {
        try {
            Client client = clientRepository.findByClientNumber(clientNumber);
            Collection<Transaction> clientTransactions = transactionRepository.findAll();
            clientTransactions.stream()
                    .filter(transaction -> transaction.getClient().equals(client))
                    .forEach(transaction -> log.info(transaction.toString()));
            // Sort transactions by date
            return clientTransactions;
        } catch (Exception e) {
            log.error("Error in ManageService.showClientTransactions: " + e.getMessage());
            return null;
        }
    }

    public Collection<Transaction> showClientTransactionsByBankAccount(String clientNumber, String bankAccountNumber) {
        try {
            Client client = clientRepository.findByClientNumber(clientNumber);
            Collection<Transaction> clientTransactions = transactionRepository.findAll();
            clientTransactions.stream()
                    .filter(transaction -> transaction.getClient().equals(client))
                    .filter(transaction -> transaction.getBankAccount().getAccountNumber().equals(bankAccountNumber))
                    .forEach(transaction -> log.info(transaction.toString()));
            // Sort transactions by date
            return clientTransactions;
        } catch (Exception e) {
            log.error("Error in ManageService.showClientTransactionsByBankAccount: " + e.getMessage());
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
        log.info("Bank account created for client: " + clientNumber + " with number: " + bankAccount.getAccountNumber()
                + " by manager: " + client.getManager().getManagerNumber());
    }
}
