package ru.rtu_mirea.javabank.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.rtu_mirea.javabank.dto.ClientDTO;
import ru.rtu_mirea.javabank.dto.UserDTO;
import ru.rtu_mirea.javabank.entity.bankEntity.*;
import ru.rtu_mirea.javabank.entity.systemEntities.Group;
import ru.rtu_mirea.javabank.entity.systemEntities.User;
import ru.rtu_mirea.javabank.repository.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {
    private final ClientRepository clientRepository;
    private final TransactionRepository transactionRepository;
    private final BankAccountRepository bankAccountRepository;
    private final UserRepository userRepository;
    private final DebitCardRepository debitCardRepository;
    private final GroupRepository groupRepository;
    private final ManagerRepository managerRepository;

    public boolean createUser(UserDTO userDTO) {
        try {
            User user = new User();
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setTelephoneNumber(userDTO.getTelephoneNumber());
            user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
            user.setActive(true);
            Set<Group> userGroups = new HashSet<>();
            userGroups.add(groupRepository.findByCode("client"));
            user.setUserGroups(userGroups);
            userRepository.save(user);
            log.info("User created: " + userDTO.getEmail());
            return true;
        } catch (Exception e) {
            log.error("Error in AdminService.createUser: " + e.getMessage());
            return false;
        }
    }

    public boolean createClient(ClientDTO clientDTO) {
        try {
            User userToClient = userRepository.findByEmail(clientDTO.getEmail());
            Client client = new Client();
            client.setUser(userToClient);
            client.setPassportNumber(client.getPassportNumber());
            client.setClientNumber("C" + userRepository.count() + 1);
            clientRepository.save(client);
            log.info("Client created: " + clientDTO.getEmail());
            return true;
        } catch (Exception e) {
            log.error("Error in AdminService.createClient: " + e.getMessage());
            return false;
        }
    }

    public boolean createManager(String email) {
        try {
            User clientToManager = userRepository.findByEmail(email);
            Set<Group> clientGroups = clientToManager.getUserGroups();
            clientGroups.add(groupRepository.findByCode("manager"));
            clientToManager.setUserGroups(clientGroups);
            Manager manager = new Manager();
            manager.setUser(clientToManager);
            manager.setManagerNumber("M" + userRepository.count() + 1);
            managerRepository.save(manager);
            userRepository.save(clientToManager);
            log.info("Client " + clientToManager.getEmail() + " is a manager");
            return true;
        } catch (Exception e) {
            log.error("Error in AdminService.createManager: " + e.getMessage());
            return false;
        }
    }

    public Collection<Client> showAllClients() {
        return clientRepository.findAll();
    }

    public Collection<Transaction> showAllTransactions() {
        Collection<Transaction> transactions = transactionRepository.findAll();
        transactions.stream().sorted((o1, o2) -> o2.getTransactionDate().compareTo(o1.getTransactionDate()));
        return transactions;
    }

    public Collection<BankAccount> showAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    public Collection<DebitCard> showAllDebitCards() {
        return debitCardRepository.findAll();
    }

    public boolean blockUser (Long id) {
        try {
            User user = userRepository.findById(id).orElseThrow();
            user.setActive(false);
            userRepository.save(user);
            log.info("User " + user.getEmail() + " blocked");
            return true;
        } catch (Exception e) {
            log.error("Error in AdminService.blockUser: " + e.getMessage());
            return false;
        }
    }

    public boolean unblockUser (Long id) {
        try {
            User user = userRepository.findById(id).orElseThrow();
            user.setActive(true);
            userRepository.save(user);
            log.info("User " + user.getEmail() + " unblocked");
            return true;
        } catch (Exception e) {
            log.error("Error in AdminService.unblockUser: " + e.getMessage());
            return false;
        }
    }
}
