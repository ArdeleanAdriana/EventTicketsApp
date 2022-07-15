package com.example.demo.controller.impl;

import com.example.demo.controller.CustomerApi;
import com.example.demo.dto.CustomerDto;
import com.example.demo.service.impl.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@CrossOrigin
public class CustomerController implements CustomerApi {
    private final CustomerService customerService;

    @Override
    public ResponseEntity<List<CustomerDto>> findAllCustomers() {
        List<CustomerDto> customerDtos = customerService.findAll();
        return new ResponseEntity<>(customerDtos, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<CustomerDto> findCustomerByUid(String customerUid) {
        return new ResponseEntity<>(customerService.findByUid(customerUid), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomerDto> findCustomerByEmail(String email) {
        return new ResponseEntity<>(customerService.findByEmail(email), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteCustomerByUid(@PathVariable String customerUid) {
        customerService.deleteByUid(customerUid);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Override
    public ResponseEntity<CustomerDto> editCustomer(@Valid CustomerDto customerDto, String customerUid) {
        return new ResponseEntity<>(customerService.update(customerDto, customerUid), HttpStatus.OK);
    }
}
