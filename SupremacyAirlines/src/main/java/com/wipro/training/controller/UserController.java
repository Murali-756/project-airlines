package com.wipro.training.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.training.exception.ResourceNotFoundException;

import com.wipro.training.model.User;
import com.wipro.training.service.UserService;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins="http://localhost:4200")
public class UserController {
/*
	private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> createUser(@Validated @RequestBody User user){
		try {
			User registeredUser=userService.registerUser(user);
			if(registeredUser != null) {
				return ResponseEntity.ok("Registration Successfull");
			} else {
				return ResponseEntity.badRequest().body("Registration Failed ");
			}}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
					body("An Error Occurred : "+e.getMessage());
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@Validated @RequestBody User user)
			throws ResourceNotFoundException{
		
		String email = user.getEmail();
		String password = user.getPassword();
		
		User u=userService.loginUser(email).orElseThrow(() ->
			new ResourceNotFoundException("User doesn't exist ::"+email));
		
		if(email.equals(u.getEmail()) && password.equals(u.getPassword())) {
			return ResponseEntity.ok("Login Successfully");
		}else {
			
			return ResponseEntity.ok("Invalid Credientials");
		}
		
	}
	// http://localhost:8088/api/user/forgot-password
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String resetToken = userService.generateResetToken(email);
        
        if (resetToken != null) {
            // In a real application, you would send this token via email.
            return ResponseEntity.ok("Password reset token generated. Token: " + resetToken);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with email " + email + " not found.");
        }
    }

    // http://localhost:8088/api/user/reset-password
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        String newPassword = request.get("newPassword");
        
        boolean result = userService.resetPassword(token, newPassword);
        if (result) {
            return ResponseEntity.ok("Password reset successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired reset token");
        }
    }*/
	private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse> createUser(@Validated @RequestBody User user){
		try {
			User registeredUser=userService.registerUser(user);
			if(registeredUser != null) {
				return ResponseEntity.ok(new ApiResponse("Registration Successfull"));
			} else {
				return ResponseEntity.badRequest().body(new ApiResponse("Registration Failed "));
			}}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
					body(new ApiResponse("An Error Occurred : "+e.getMessage()));
		}
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse> loginUser(@Validated @RequestBody User user)
			throws ResourceNotFoundException{
		
		String email = user.getEmail();
		String password = user.getPassword();
		
		User u=userService.loginUser(email).orElseThrow(() ->
			new ResourceNotFoundException("User doesn't exist ::"+email));
		
		if(email.equals(u.getEmail()) && password.equals(u.getPassword())) {
			return ResponseEntity.ok(new ApiResponse("Login Successfully"));
		}else {
			
			return ResponseEntity.ok(new ApiResponse("Invalid Credientials"));
		}
		
	}
	
	
	@PostMapping("/forgot-password")
    public ResponseEntity<Map<String, String>> forgotPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String resetToken = userService.generateResetToken(email);
        
        if (resetToken != null) {
            // Return token explicitly in response, not just in the message
            return ResponseEntity.ok(Map.of("message", "Password reset token generated.", "token", resetToken));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "User with email " + email + " not found."));
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse> resetPassword(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        String newPassword = request.get("newPassword");
        
        boolean result = userService.resetPassword(token, newPassword);
        if (result) {
            return ResponseEntity.ok(new ApiResponse("Password reset successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Invalid or expired reset token"));
        }
    }
	
    
    
    
    
    //these are changes made on 17/09 crosscheck for suman or murali for previous things
 // Define the response structure
    static class ApiResponse {
        private String message;

        public ApiResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
    
    

}