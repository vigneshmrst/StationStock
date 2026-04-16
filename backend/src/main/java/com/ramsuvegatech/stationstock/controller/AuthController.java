package com.ramsuvegatech.stationstock.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramsuvegatech.stationstock.dto.LoginRequest;
import com.ramsuvegatech.stationstock.dto.RegisterRequest;
import com.ramsuvegatech.stationstock.dto.UserSummary;
import com.ramsuvegatech.stationstock.model.User;
import com.ramsuvegatech.stationstock.service.AuthService;
import com.ramsuvegatech.stationstock.utils.JwtUtils;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
@Autowired
	private  AuthService authService;
@Autowired
 private JwtUtils jwtUtil;

// Register endpoint ----> create a new user account

	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
		System.out.println("Received registration request: " + request);
		UserSummary user = authService.register(request);

		return ResponseEntity.status(HttpStatus.CREATED).body(user);

	}
	   
	   
// Login endpoint ---> authenticate user and return JWT token
	   
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
		Optional<User> userOpt = authService.login(request);
		if (userOpt.isPresent()) {
			String token = jwtUtil.generateToken(userOpt.get().getEmail());
			Map<String, String> response = new HashMap<>();
			response.put("token", token);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);

		} else {
			throw new RuntimeException("Invalid credentials");
		}
	   }
	
}
