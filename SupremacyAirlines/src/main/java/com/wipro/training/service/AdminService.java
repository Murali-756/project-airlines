package com.wipro.training.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wipro.training.model.Admin;

import com.wipro.training.repository.AdminRepository;



@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepo;
	
	public Optional<Admin> loginAdmin(String username) {
		return adminRepo.findByUsername(username);
	}
	public Admin registerAdmin(Admin admin) {
		return adminRepo.save(admin);
	}
	
	   
/*
	    public Optional<Admin> authenticate(String username, String password) {
	        Optional<Admin> admin = adminRepo.findByUsername(username);
	        if (admin != null && password.matches(password, admin.getPassword())) {
	            return admin;
	        }
	        return null;
	    }*/
}
