package com.wipro.training.model;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Base64;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY,generator="user_seq")
	@SequenceGenerator(initialValue=1001,allocationSize=1, name = "user_seq")

    private Long id;
    
    
    private String title;
    
    @Column(name="first_name")
    private String firstName;
    
    @Column(name="last_name")
    private String lastName;
    
    
    @Column(unique=true)
    private String email;
    
    @Column(name="reset_token")
    private String resetToken;

    @Column(name="token_expiry_date")
    private LocalDate tokenExpiryDate;

   
    @Column(name="password")
    private String password;
    
    @Column(name="phone",unique=true)
    private String phoneNumber;
   
    @JsonFormat(pattern="yyyy-MM-dd")

    private LocalDate dateOfBirth;
	public User(Long id, String title, String firstName, String lastName, String email, String password,
			String phoneNumber, LocalDate dateOfBirth,String resetToken ,LocalDate tokenExpiryDate) {
		super();
		this.id = id;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.resetToken = resetToken;
		this.tokenExpiryDate = tokenExpiryDate;
	}
	public User() { 
		
	}
	public Long getId() {
		return id;
	}
	public void setUserId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getResetToken() {
		return resetToken;
	}
	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}
	public LocalDate getTokenExpiryDate() {
		return tokenExpiryDate;
	}
	public void setTokenExpiryDate(LocalDate tokenExpiryDate) {
		this.tokenExpiryDate = tokenExpiryDate;
	}
  
}

