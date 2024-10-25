package com.sakib.auth_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Person {
    @Id
    private Integer mobile;
    private String name;
    private String email;
    private String password;

    public Person(Integer mobile, String name, String email, String password) {
        this.mobile = mobile;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Integer getMobile() {
        return mobile;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
