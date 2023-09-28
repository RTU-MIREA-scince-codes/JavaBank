package ru.rtu_mirea.javabank.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.rtu_mirea.javabank.dto.ClientDTO;
import ru.rtu_mirea.javabank.dto.UserDTO;
import ru.rtu_mirea.javabank.service.AdminService;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/create/user")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        try {
            if (adminService.createUser(userDTO)) {
                return ResponseEntity.ok("User created successfully");
            } else {
                return ResponseEntity.badRequest().body("User creation failed");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/create/client")
    public ResponseEntity<?> createClient(@RequestBody ClientDTO clientDTO) {
        try {
            if (adminService.createClient(clientDTO)) {
                return ResponseEntity.ok("Client created successfully");
            } else {
                return ResponseEntity.badRequest().body("Client creation failed");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/create/manager")
    public ResponseEntity<?> createManager(@RequestBody String email) {
        try {
            if (adminService.createManager(email)) {
                return ResponseEntity.ok("Manager created successfully");
            } else {
                return ResponseEntity.badRequest().body("Manager creation failed");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/show/clients")
    public ResponseEntity<?> showClients() {
        try {
            return ResponseEntity.ok(adminService.showAllClients());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/show/transactions")
    public ResponseEntity<?> showTransactions() {
        try {
            return ResponseEntity.ok(adminService.showAllTransactions());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/show/bankAccounts")
    public ResponseEntity<?> showBankAccounts() {
        try {
            return ResponseEntity.ok(adminService.showAllBankAccounts());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/show/debitCards")
    public ResponseEntity<?> showDebitCards() {
        try {
            return ResponseEntity.ok(adminService.showAllDebitCards());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/block/user")
    public ResponseEntity<?> blockUser(@RequestBody Long id) {
        try {
            if (adminService.blockUser(id)) {
                return ResponseEntity.ok("User blocked successfully");
            } else {
                return ResponseEntity.badRequest().body("User blocking failed");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/unblock/user")
    public ResponseEntity<?> unblockUser(@RequestBody Long id) {
        try {
            if (adminService.unblockUser(id)) {
                return ResponseEntity.ok("User unblocked successfully");
            } else {
                return ResponseEntity.badRequest().body("User unblocking failed");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
