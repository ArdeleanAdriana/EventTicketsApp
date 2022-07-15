package com.example.demo.service;

import com.example.demo.dto.CustomerDto;
import com.example.demo.model.Customer;

import java.util.List;

public interface CustomerServiceInterface {

    List<CustomerDto> findAll();

    CustomerDto update(CustomerDto customerDto, String Uid);

    CustomerDto findByUid(String uid);

    CustomerDto findByEmail(String email);

    void deleteByUid(String uid);

    CustomerDto mapToDTO(Customer customer);

    Customer mapToEntity(CustomerDto customerDto);

    List<CustomerDto> mapToDTOList(List<Customer> customers);
}
