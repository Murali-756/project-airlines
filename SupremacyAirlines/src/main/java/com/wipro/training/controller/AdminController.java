package com.wipro.training.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wipro.training.exception.ResourceNotFoundException;
import com.wipro.training.model.Admin;
import com.wipro.training.service.AdminService;


@RestController
@RequestMapping("api/admin")
public class AdminController {

	//private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    private  AdminService adminService;

    // Admin credentials for authentication
    private static final String ADMIN_USERNAME = "Murali123";
    private static final String ADMIN_PASSWORD = "Murali@123";

    
    private boolean authenticate(String username, String password) {
        return ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password);
    }
/*
   if (authenticate(request.getUsername(), request.getPassword())) {
        return adminService.loginAdmin(ADMIN_USERNAME);
        		
    } else {
        throw new UnauthorizedException("Invalid admin credentials"); // Handle invalid credentials
	*/
}

