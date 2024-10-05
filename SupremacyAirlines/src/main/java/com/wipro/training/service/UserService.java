package com.wipro.training.service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.wipro.training.model.User;
import com.wipro.training.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	/*
	private BCryptPasswordEncoder passwordEncoder;
	   
	 public User registerUser(RegistrationRequest registrationRequest) {
        // Validate email and password here if needed
        User user = new User();
        user.setTitle(registrationRequest.getTitle());
        user.setFirstName(registrationRequest.getFirstName());
        user.setLastName(registrationRequest.getLastName());
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setPhoneNumber(registrationRequest.getPhoneNumber());
        user.setDateOfBirth(registrationRequest.getDateOfBirth());
        
        return userRepo.save(user);
    }
	*/
	     
	public User registerUser(User user) {
		return userRepo.save(user);
	}
	
	public Optional<User> loginUser(String email) {
		return userRepo.findByEmail(email);
	}
	 public User findByEmail(String email) {
	        return userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
	    }
	 public Optional<User> UserId(Long id) {
			return userRepo.findById(id);
		}
	 
	// Generate and set reset token for the user
	    public String generateResetToken(String email) {
	        Optional<User> userOpt = userRepo.findByEmail(email);
	        if (userOpt.isPresent()) {
	            User user = userOpt.get();
	            String token = UUID.randomUUID().toString();  // Generate a unique token
	            user.setResetToken(token);
	            user.setTokenExpiryDate(LocalDate.now().plusDays(1));  // Token expires after 1 day
	            userRepo.save(user);
	            return token;
	        }
	        return null;
	    }

	    // Validate token and reset the password
	    public boolean resetPassword(String token, String newPassword) {
	        Optional<User> userOpt = userRepo.findByResetToken(token);
	        if (userOpt.isPresent()) {
	            User user = userOpt.get();
	            if (user.getTokenExpiryDate().isAfter(LocalDate.now())) {
	                user.setPassword(newPassword);  // Set the new password (hash it in real application)
	                user.setResetToken(null);  // Invalidate the token after use
	                user.setTokenExpiryDate(null);
	                userRepo.save(user);
	                return true;
	            }
	        }
	        return false;
	    }

}

