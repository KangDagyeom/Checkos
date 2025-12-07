package com.example.assignmentjav6.controller;


import com.example.assignmentjav6.entity.User;
import com.example.assignmentjav6.repo.UserRepository;
import com.example.assignmentjav6.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserRepository userRepo;
    private final BCryptPasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepo, BCryptPasswordEncoder encoder, JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> body) {
        if (userRepo.findByUsername(body.get("username")).isPresent())
            return ResponseEntity.badRequest().body("exists");
        User u = new User();
        u.setUsername(body.get("username"));
        u.setPassword(encoder.encode(body.get("password")));
        u.setFullName(body.getOrDefault("fullName", ""));
        u.setRole("ROLE_USER");
        userRepo.save(u);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        var userOpt = userRepo.findByUsername(body.get("username"));
        if (userOpt.isEmpty()) return ResponseEntity.status(401).body("bad");
        var user = userOpt.get();
        if (!encoder.matches(body.get("password"), user.getPassword())) return ResponseEntity.status(401).body("bad");
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
        return ResponseEntity.ok(Map.of("token", token, "role", user.getRole()));
    }
}

