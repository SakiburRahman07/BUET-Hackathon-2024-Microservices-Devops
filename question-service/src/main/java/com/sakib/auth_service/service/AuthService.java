package com.sakib.auth_service.service;


import com.sakib.auth_service.dao.AuthDao;
import com.sakib.auth_service.model.Person;
import com.sakib.auth_service.model.PersonLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    AuthDao authDao;

    public ResponseEntity<String> doRegistration(Person person) {
        try {
            authDao.save(person);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Registration successful");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding person: " + e.getMessage());
        }
    }

    public ResponseEntity<String> doLogin(PersonLogin personLogin) {
        // Fetch the person based on the provided email
        Person person = authDao.findByEmail(personLogin.getEmail());

        // Check if the person exists and the password matches
        if (person != null && person.getPassword().equals(personLogin.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else if (person == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid credentials");
        }
    }
}
