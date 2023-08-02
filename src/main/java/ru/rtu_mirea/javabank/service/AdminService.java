package ru.rtu_mirea.javabank.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.rtu_mirea.javabank.dto.UserDTO;
import ru.rtu_mirea.javabank.entity.bankEntity.Client;
import ru.rtu_mirea.javabank.entity.bankEntity.Transaction;
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

    public void createUser(UserDTO userDTO) {
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
    }

    public void createManager(String email) {
        User clientToManager = userRepository.findByEmail(email);
        Set<Group> clientGroups = clientToManager.getUserGroups();
        clientGroups.add(groupRepository.findByCode("manager"));
        clientToManager.setUserGroups(clientGroups);
        userRepository.save(clientToManager);
        log.info("Client " + clientToManager.getEmail() + " is a manager");
    }

    public Collection<Client> showAllClients() {
        return clientRepository.findAll();
    }

    public Collection<Transaction> showAllTransactions() {
        Collection<Transaction> transactions = transactionRepository.findAll();
        transactions.stream().sorted((o1, o2) -> o2.getTransactionDate().compareTo(o1.getTransactionDate()));
        return transactions;
    }

    public void blockUser (Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setActive(false);
        userRepository.save(user);
        log.info("User " + user.getEmail() + " blocked");
    }

    public void unblockUser (Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setActive(true);
        userRepository.save(user);
        log.info("User " + user.getEmail() + " unblocked");
    }
}
