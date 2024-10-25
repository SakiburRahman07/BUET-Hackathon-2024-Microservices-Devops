package com.sakib.auth_service.dao;


import com.sakib.auth_service.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthDao extends JpaRepository<Person,Integer> {
    Person findByEmail(String email);
}
