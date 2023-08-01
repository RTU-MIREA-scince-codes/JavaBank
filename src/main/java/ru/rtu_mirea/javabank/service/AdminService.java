package ru.rtu_mirea.javabank.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.rtu_mirea.javabank.dto.UserDTO;
import ru.rtu_mirea.javabank.entity.systemEntities.User;
import ru.rtu_mirea.javabank.repository.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {
    private final ClientRepository clientRepository;
    private final TransactionRepository transactionRepository;
    private final BankAccountRepository bankAccountRepository;
    private final UserRepository userRepository;
    private final DebitCardRepository debitCardRepository;

    public void createUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setTelephoneNumber(userDTO.getTelephoneNumber());
        user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
        user.setActive(true);
        // user.setRoles(new ArrayList<>());
        userRepository.save(user);
        log.info("User created: " + userDTO.getEmail());
    }
}
