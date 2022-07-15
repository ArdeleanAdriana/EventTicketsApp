package com.example.demo.service.impl;

import com.example.demo.dto.CustomerDto;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepo;
import com.example.demo.repository.TicketRepo;
import com.example.demo.service.CustomerServiceInterface;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerService implements CustomerServiceInterface {
    private final CustomerRepo customerRepo;
    private final ModelMapper mapper;
    private final TicketRepo ticketRepo;


    public List<CustomerDto> findAll() {
        List<Customer> students = customerRepo.findAll();
        return mapToDTOList(students);
    }

    public CustomerDto update(CustomerDto customerDto, String Uid) {
        Customer oldCustomer = customerRepo.findCustomerByUid(Uid).orElseThrow(() -> new CustomerNotFoundException(Uid));
        Customer updatedCustomer = mapToEntity(customerDto);
        updatedCustomer.setId(oldCustomer.getId());
        updatedCustomer.setUid(oldCustomer.getUid());
        updatedCustomer.setPassword(oldCustomer.getPassword());
        updatedCustomer.setRole(oldCustomer.getRole());
        return mapToDTO(customerRepo.save(updatedCustomer));
    }

    public CustomerDto findByUid(String uid) {
        return mapToDTO(customerRepo.findCustomerByUid(uid).orElseThrow(() -> new CustomerNotFoundException(uid)));
    }

    public CustomerDto findByEmail(String email) {
        return mapToDTO(customerRepo.findCustomerByEmail(email).orElseThrow(() -> new CustomerNotFoundException(email)));
    }

    @Transactional
    public void deleteByUid(String uid) {
        Customer customer = customerRepo.findCustomerByUid(uid).orElseThrow(() -> new CustomerNotFoundException(uid));
        ticketRepo.deleteAllByCustomer(customer);
        customerRepo.delete(customerRepo.findCustomerByUid(uid).orElseThrow(() -> new CustomerNotFoundException(uid)));
    }


    public CustomerDto mapToDTO(Customer customer) {
        return CustomerDto.builder()
                .uid(customer.getUid())
                .name(customer.getName())
                .ron(customer.getRon())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .build();
    }

    public Customer mapToEntity(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setUid(customerDto.getUid());
        customer.setName(customerDto.getName());
        customer.setRon(customerDto.getRon());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone(customerDto.getPhone());
        return customer;
    }

    public List<CustomerDto> mapToDTOList(List<Customer> customers) {
        List<CustomerDto> dtos = customers.stream().map(customer -> mapper.map(customer, CustomerDto.class)).collect(Collectors.toList());
        return dtos;
    }
}
