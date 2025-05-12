package com.vehicle.vechiclemanagement.controller;

import com.vehicle.vechiclemanagement.dto.LoginRequest;
import com.vehicle.vechiclemanagement.dto.LoginResponse;
import com.vehicle.vechiclemanagement.model.User;
import com.vehicle.vechiclemanagement.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        System.out.println("success");
        return ResponseEntity.ok(userService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        userService.register(user);
        return ResponseEntity.ok("Registration successful. Please login.");
    }

    @GetMapping("/user-by-email/{email}")
    public ResponseEntity<LoginResponse> getUserByEmail(@PathVariable String email) {
        User user = userService.findByEmail(email);
        LoginResponse response = new LoginResponse(null, user.getRole(), null, user.getId());
        return ResponseEntity.ok(response);
    }
}