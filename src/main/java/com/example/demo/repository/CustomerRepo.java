package com.example.demo.repository;

import com.example.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerByUid(String uid);

    Optional<Customer> findCustomerByEmail(String email);

    void deleteByUid(String uid);

}
