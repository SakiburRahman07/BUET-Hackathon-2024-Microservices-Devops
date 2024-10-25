package com.sakib.train_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "otp-service")

public interface OTPClient {
    @PostMapping("/payment/{otp}")
    String getPayment(@PathVariable("otp") int otp);
}
