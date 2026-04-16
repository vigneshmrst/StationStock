package com.ramsuvegatech.stationstock.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ramsuvegatech.stationstock.dto.LoginRequest;
import com.ramsuvegatech.stationstock.dto.RegisterRequest;
import com.ramsuvegatech.stationstock.dto.UserSummary;
import com.ramsuvegatech.stationstock.exception.EmailAlreadyExistException;
import com.ramsuvegatech.stationstock.model.Role;
import com.ramsuvegatech.stationstock.model.User;
import com.ramsuvegatech.stationstock.repository.UserRepository;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class AuthService {
	
@Autowired
    private  UserRepository userRepository ;

    //private  PasswordEncoder passwordEncoder;

    
	@Transactional
	public UserSummary register(RegisterRequest request) {
		// Check duplicate email — case-insensitive
		String email = request.getEmail().toLowerCase().trim();
		if (userRepository.existsByEmail(email)) {
			throw new EmailAlreadyExistException(email);
		}

		User user = new User();
		user.setFirstName(request.getFirstName().trim());
		user.setLastName(request.getLastName().trim());
		user.setEmail(email);
		user.setPassword(request.getPassword());
		user.setRole(Role.EMPLOYEE); // All new users start as EMPLOYEE

		User savedUser = userRepository.save(user);

		UserSummary userSummary = new UserSummary();
		userSummary.setId(savedUser.getId());
		userSummary.setFirstName(savedUser.getFirstName());
		userSummary.setLastName(savedUser.getLastName());
		userSummary.setEmail(savedUser.getEmail());
		userSummary.setRole(savedUser.getRole());
		userSummary.setCreatedAt(savedUser.getCreatedAt());

		return userSummary;
	}
	
	
	
	public Optional<User> login(LoginRequest request) {
		Optional<User> user = userRepository.findByEmail(request.getEmail());
		if (user.isPresent() && user.get().getPassword().equals(request.getPassword())) {
			return user;
		}
		return Optional.empty();
	}




}