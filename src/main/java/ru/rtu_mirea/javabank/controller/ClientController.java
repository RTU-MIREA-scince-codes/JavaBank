package ru.rtu_mirea.javabank.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.rtu_mirea.javabank.dto.TransactionDTO;
import ru.rtu_mirea.javabank.service.ClientService;

@Controller
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/show/bankAccounts/{clientNumber}")
    public ResponseEntity<?> showClientBankAccounts(@PathVariable String clientNumber) {
        try {
            return ResponseEntity.ok(clientService.getBankAccounts(clientNumber));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/create/bankAccount/{clientNumber}")
    public ResponseEntity<?> createClientBankAccount(@PathVariable String clientNumber) {
        try {
            if (clientService.createBankAccount(clientNumber)) {
                return ResponseEntity.ok("Bank account created successfully");
            } else {
                return ResponseEntity.badRequest().body("Bank account creation failed");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/transaction")
    public ResponseEntity<?> transactionToBankAccount(@RequestBody TransactionDTO transactionDTO) {
        try {
            clientService.transactionToBankAccount(transactionDTO.getBankAccountId(),
                    transactionDTO.getBankAccountToId(), transactionDTO.getAmount());
            return ResponseEntity.ok("Transaction completed successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/show/transactions/{clientNumber}")
    public ResponseEntity<?> showClientTransactions(@PathVariable String clientNumber) {
        try {
            return ResponseEntity.ok(clientService.getTransactions(clientNumber));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
