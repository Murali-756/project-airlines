package com.wipro.training.service;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.wipro.training.Dto.PaymentRequest;
import com.wipro.training.Dto.PaymentResponse;

@Service
public class PaymentService {

	public PaymentResponse processPayment(PaymentRequest paymentRequest) {
		PaymentResponse paymentResponse = new PaymentResponse();

		if ("UPI".equalsIgnoreCase(paymentRequest.getPaymentType())) {
			// Simulate UPI payment processing with validation
			if (isValidUpiId(paymentRequest.getUpiId())) {
				paymentResponse.setSuccess(true);
				paymentResponse.setMessage("UPI Payment successful.");
				paymentResponse.setTransactionId(UUID.randomUUID().toString());
			} else {
				paymentResponse.setSuccess(false);
				paymentResponse.setMessage("Invalid UPI ID.");
			}
		} else if ("CARD".equalsIgnoreCase(paymentRequest.getPaymentType())) {
			// Simulate card payment processing with simple validations
			if (isValidCardDetails(paymentRequest)) {
				paymentResponse.setSuccess(true);
				paymentResponse.setMessage("Card Payment successful.");
				paymentResponse.setTransactionId(UUID.randomUUID().toString());
			} else {
				paymentResponse.setSuccess(false);
				paymentResponse.setMessage("Invalid card details.");
			}
		} else {
			paymentResponse.setSuccess(false);
			paymentResponse.setMessage("Unsupported payment type.");
		}

		return paymentResponse;
	}

	// Utility method to validate card details
	private boolean isValidCardDetails(PaymentRequest paymentRequest) {
		String cardNumber = paymentRequest.getCardNumber();
		String expiryDate = paymentRequest.getExpiryDate();
		String cvv = paymentRequest.getCvv();

		// Validate card number: 16 digits
		if (cardNumber == null || cardNumber.length() != 16 || !cardNumber.matches("\\d{16}")) {
			return false; // Invalid card number
		}

		// Validate expiry date: MM/YY format and must not be expired
		if (!isValidExpiryDate(expiryDate)) {
			return false; // Invalid or expired date
		}

		// Validate CVV: 3 digits
		if (cvv == null || cvv.length() != 3 || !cvv.matches("\\d{3}")) {
			return false; // Invalid CVV
		}

		return true;
	}

	// Validate UPI ID
	private boolean isValidUpiId(String upiId) {
		// Check if UPI ID contains '@' and is in the format 'someid@domain'
		return upiId != null && upiId.matches("^[\\w\\-\\.]+@[\\w\\-]+$");
	}

	// Validate Expiry Date (MM/YY format and future date)
	private boolean isValidExpiryDate(String expiryDate) {
		if (expiryDate == null || !expiryDate.matches("(0[1-9]|1[0-2])/([0-9]{2})")) {
			return false; // Invalid format
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
		YearMonth expiry = YearMonth.parse(expiryDate, formatter);
		YearMonth currentMonth = YearMonth.now();

		return expiry.isAfter(currentMonth); // Expiry must be in the future
	}
}
