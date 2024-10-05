package com.wipro.training.model;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Admin {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
    private String username;
    private String password; // Store hashed passwords
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		Base64.Encoder encoder = Base64.getEncoder();   // Use Base64 class for password Encryption
		String normalString = password;
		String encodedString = encoder.encodeToString(   // encrypt password in database field
				normalString.getBytes(StandardCharsets.UTF_8) );
		this.password = encodedString;
		//this.password=password;
	}
	
	

    
}
