package com.wipro.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.training.Dto.PaymentRequest;
import com.wipro.training.Dto.PaymentResponse;
import com.wipro.training.service.PaymentService;
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

	@Autowired
    private PaymentService paymentService;

	// http://localhost:8088/api/payment/process
    @PostMapping("/process")
    public PaymentResponse processPayment(@RequestBody PaymentRequest paymentRequest) {
        return paymentService.processPayment(paymentRequest);
    }
}
