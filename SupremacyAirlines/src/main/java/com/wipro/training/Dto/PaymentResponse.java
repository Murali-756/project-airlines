package com.wipro.training.Dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PaymentResponse {

	@Id
	private Long id;
	 private boolean success;
	    private String message;
	    private String transactionId;

	    // Getters and Setters
	    public boolean isSuccess() {
	        return success;
	    }

	    public void setSuccess(boolean success) {
	        this.success = success;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }

	    public String getTransactionId() {
	        return transactionId;
	    }

	    public void setTransactionId(String transactionId) {
	        this.transactionId = transactionId;
	    }
}
