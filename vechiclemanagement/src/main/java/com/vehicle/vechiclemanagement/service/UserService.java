package com.vehicle.vechiclemanagement.service;

import com.vehicle.vechiclemanagement.dto.LoginRequest;
import com.vehicle.vechiclemanagement.dto.LoginResponse;
import com.vehicle.vechiclemanagement.model.User;
import com.vehicle.vechiclemanagement.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

import javax.crypto.SecretKey;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecretKey jwtSecret;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       @Value("${jwt.secret}") String jwtSecretBase64) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecretBase64);
        this.jwtSecret = Keys.hmacShaKeyFor(keyBytes); // Use secure key for HS512
    }

    @Bean
    public CommandLineRunner initializeAdmins() {
        return args -> {
            if (!userRepository.existsByEmail("hariniarunachalam544@gmail.com")) {
                User admin1 = new User();
                admin1.setFirstName("Harini");
                admin1.setLastName("Arunachalam");
                admin1.setEmail("hariniarunachalam544@gmail.com");
                admin1.setPassword(passwordEncoder.encode("harini@544"));
                admin1.setRole("ADMIN");
                userRepository.save(admin1);
            }

            if (!userRepository.existsByEmail("nameeras2006@gmail.com")) {
                User admin2 = new User();
                admin2.setFirstName("Nameera");
                admin2.setLastName("S");
                admin2.setEmail("nameeras2006@gmail.com");
                admin2.setPassword(passwordEncoder.encode("nameera@786"));
                admin2.setRole("ADMIN");
                userRepository.save(admin2);
            }

            if (!userRepository.existsByEmail("sukanthak19@gmail.com")) {
                User admin3 = new User();
                admin3.setFirstName("Sukantha");
                admin3.setLastName("K");
                admin3.setEmail("sukanthak19@gmail.com");
                admin3.setPassword(passwordEncoder.encode("sukantha@19"));
                admin3.setRole("ADMIN");
                userRepository.save(admin3);
            }
        };
    }

    public User register(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("CUSTOMER");
        return userRepository.save(user);
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24 hours
                .signWith(jwtSecret, SignatureAlgorithm.HS512)
                .compact();

        String redirectUrl = "dashboardcust.html";
        if ("ADMIN".equals(user.getRole())) {
            redirectUrl = "/admin-dashboard";
        } else if ("PREMIUM".equals(user.getRole())) {
            redirectUrl = "/premium-dashboard";
        }

        return new LoginResponse(token, user.getRole(), redirectUrl, user.getId()); // Include userId
    }

    // Add method to find user by email for the fallback endpoint
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}