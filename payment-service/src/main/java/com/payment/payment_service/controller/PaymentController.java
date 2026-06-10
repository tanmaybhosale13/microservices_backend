package com.payment.payment_service.controller;

import com.payment.payment_service.dto.PaymentRequest;
import com.payment.payment_service.dto.PaymentResponse;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {


    @PostMapping("/process")
    public PaymentResponse processPayment(@RequestBody PaymentRequest request) {

        PaymentResponse response = new PaymentResponse();

        // Simulate payment success for any positive amount
        if (request.getAmount() != null && request.getAmount() > 0) {
            response.setStatus("SUCCESS");
            response.setTransactionId(UUID.randomUUID().toString());
        } else {
            response.setStatus("FAILED");
            response.setTransactionId(null);
        }

        return response;
    }


    @GetMapping("/process/{orderId}")
    public String process(@PathVariable Long orderId) {

        // simulate failure
        if (orderId % 2 == 0) {
            throw new RuntimeException("Payment Service Failed");
        }

        return "PAYMENT SUCCESS for order " + orderId;
    }


    @GetMapping("/test")
    public String test() {
        return "Payment Service Working";
    }

}
