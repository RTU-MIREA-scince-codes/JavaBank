package ru.rtu_mirea.javabank.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.rtu_mirea.javabank.service.ManagerService;

@Controller
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;

    @GetMapping("/show/transactions/{clientNumber}")
    public ResponseEntity<?> showClientTransactions(@PathVariable String clientNumber) {
        try {
            return ResponseEntity.ok(managerService.showClientTransactions(clientNumber));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/show/bankAccounts/{clientNumber}")
    public ResponseEntity<?> showClientBankAccounts(@PathVariable String clientNumber) {
        try {
            return ResponseEntity.ok(managerService.showClientBankAccounts(clientNumber));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/show/{bankAccountId}/{clientNumber}")
    public ResponseEntity<?> showBankAccount(@PathVariable String bankAccountId, @PathVariable String clientNumber) {
        try {
            return ResponseEntity.ok(managerService.showClientBankAccount(clientNumber, bankAccountId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/show/{bankAccountId}/transactions/{clientNumber}")
    public ResponseEntity<?> showBankAccountTransactions(@PathVariable String bankAccountId, @PathVariable String clientNumber) {
        try {
            return ResponseEntity.ok(managerService.showClientTransactionsByBankAccount(clientNumber, bankAccountId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/create/bankAccount/{clientNumber}")
    public ResponseEntity<?> createClientBankAccount(@PathVariable String clientNumber) {
        try {
            if (managerService.createBankAccount(clientNumber)) {
                return ResponseEntity.ok("Bank account created successfully");
            } else {
                return ResponseEntity.badRequest().body("Bank account creation failed");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
