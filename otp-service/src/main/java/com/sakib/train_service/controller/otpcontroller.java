package com.sakib.train_service.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class otpcontroller {

    @PostMapping("/payment/{otp}")
    public String getPayment(@PathVariable int otp) {
        if (otp == 1234) {
            return "Payment Successful";
        }
        return "Failed";
    }
}
