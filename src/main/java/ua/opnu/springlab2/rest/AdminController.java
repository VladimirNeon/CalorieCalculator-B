package ua.opnu.springlab2.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.opnu.springlab2.model.UserEntity;
import ua.opnu.springlab2.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/admin/users")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    // Отримати всіх користувачів
    @GetMapping
    @PreAuthorize("hasRole('Admin')")
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    // Видалити користувача за ID
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("{}");
    }
}
