package com.sakib.auth_service.controller;

import com.sakib.auth_service.model.Person;
import com.sakib.auth_service.model.PersonLogin;
import com.sakib.auth_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173") // Allow requests from your React app
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/registration")  // Ensure the path starts with a /
    public ResponseEntity<String> registration(@RequestBody Person person) {
        return authService.doRegistration(person);
    }

    @PostMapping("/login")  // Ensure the path starts with a /
    public ResponseEntity<String> login(@RequestBody PersonLogin personLogin) {
        return authService.doLogin(personLogin);
    }
}
