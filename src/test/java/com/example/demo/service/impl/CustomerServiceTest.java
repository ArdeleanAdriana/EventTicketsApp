package com.example.demo.service.impl;

import com.example.demo.dto.CustomerDto;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepo;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;
    @Mock
    private CustomerRepo customerRepo;


    @Test
    public void update_givenUidAndUpdatedValues_expectTheUpdatedCustomer() throws ParseException {
        CustomerDto customerDto = CustomerDto.builder()
                .email("adsa@yahoo.com")
                .name("aaa")
                .ron(22)
                .phone("0755476116")
                .build();

        Customer customer = new Customer();

        customer.setUid("22");
        customer.setName("aaa");
        customer.setRon(22);
        customer.setEmail("adsa@yahoo.com");
        customer.setPhone("0755476116");


        when(customerRepo.findCustomerByUid("22")).thenReturn(Optional.of(customer));
        when(customerRepo.save(any(Customer.class))).thenReturn(customer);

        customerDto.setUid("22");
        CustomerDto result = customerService.update(customerDto, "22");

        verify(customerRepo).save(any(Customer.class));

        assertEquals(customerDto, result);
    }

    @Test
    public void findByUid_givenUid_expectTheCustomer() throws ParseException {

        Customer customer = new Customer();

        customer.setUid("22");
        customer.setName("aaa");
        customer.setRon(22);
        customer.setEmail("adsa@yahoo.com");
        customer.setPhone("0755476116");


        when(customerRepo.findCustomerByUid(anyString())).thenReturn(Optional.of(customer));

        CustomerDto fetchedCustomer = customerService.findByUid(customer.getUid());

        verify(customerRepo).findCustomerByUid("22");

        assertEquals(fetchedCustomer.getName(), customer.getName());
        assertEquals(fetchedCustomer.getEmail(), customer.getEmail());
        assertEquals((int) (fetchedCustomer.getRon()), (int) (customer.getRon()));
        assertEquals(fetchedCustomer.getPhone(), customer.getPhone());

    }

    @Test
    public void findByEmail_givenUid_expectTheCustomer() throws ParseException {

        Customer customer = new Customer();

        customer.setUid("2");
        customer.setName("aaa");
        customer.setRon(22);
        customer.setEmail("adsa@yahoo.com");
        customer.setPhone("0755476116");


        when(customerRepo.findCustomerByEmail(anyString())).thenReturn(Optional.of(customer));

        CustomerDto fetchedCustomer = customerService.findByEmail(customer.getEmail());

        verify(customerRepo).findCustomerByEmail("adsa@yahoo.com");

        assertEquals(fetchedCustomer.getName(), customer.getName());
        assertEquals(fetchedCustomer.getUid(), customer.getUid());
        assertEquals((int) (fetchedCustomer.getRon()), (int) (customer.getRon()));
        assertEquals(fetchedCustomer.getPhone(), customer.getPhone());

    }

    @Test
    public void findAll_givenCustomers_expectTheCustomers() throws ParseException {

        Customer customer1 = new Customer();

        customer1.setUid("ss");
        customer1.setName("aaa");
        customer1.setRon(22);
        customer1.setEmail("adsayahoo.com");
        customer1.setPhone("0755476116");

        Customer customer2 = new Customer();

        customer2.setUid("aa");
        customer2.setName("dasfasfas");
        customer2.setRon(33);
        customer2.setEmail("asadasd@yahoo.com");
        customer2.setPhone("0733376116");


        List<Customer> customers = Arrays.asList(customer1, customer2);

        when(customerRepo.findAll()).thenReturn(customers);
        List<CustomerDto> resultedValue = customerService.findAll();
        Assertions.assertEquals(customers.size(), resultedValue.size());
    }

    @Test
    public void deleteByUid_givenUid_expectStatusOk() throws ParseException {
        Customer customer = new Customer();

        customer.setUid("22");
        customer.setName("aaa");
        customer.setRon(22);
        customer.setEmail("adsa@yahoo.com");
        customer.setPhone("0755476116");

        when(customerRepo.findCustomerByUid(anyString())).thenReturn(Optional.of(customer));

        customerService.deleteByUid(customer.getUid());
        verify(customerRepo).delete(customer);
    }


    @Test
    public void fromEntityToDto_givenEntity_expectDto() throws ParseException {
        Customer customer = new Customer();

        customer.setUid("22");
        customer.setName("aaa");
        customer.setRon(22);
        customer.setEmail("adsa@yahoo.com");
        customer.setPhone("0755476116");

        CustomerDto customerDto = customerService.mapToDTO(customer);

        assertEquals(customerDto.getName(), customer.getName());
        assertEquals(customerDto.getEmail(), customer.getEmail());
        assertEquals((int) (customerDto.getRon()), (int) (customer.getRon()));
        assertEquals(customerDto.getPhone(), customer.getPhone());

    }

    @Test
    public void fromDtoToEntity_givenDto_expectEntity() throws ParseException {
        CustomerDto customerDto = CustomerDto.builder()
                .uid("22")
                .email("adsa@yahoo.com")
                .name("aaa")
                .ron(22)
                .phone("0755476116")
                .build();


        Customer customer = customerService.mapToEntity(customerDto);

        assertEquals(customerDto.getName(), customer.getName());
        assertEquals(customerDto.getEmail(), customer.getEmail());
        assertEquals(customerDto.getPhone(), customer.getPhone());
        assertEquals((int) (customerDto.getRon()), (int) (customer.getRon()));

    }


    @Test(expected = CustomerNotFoundException.class)
    public void findByUid_givenUid_expectCustomerNotFoundException() {
        when(customerRepo.findCustomerByUid(anyString())).thenReturn(Optional.empty());
        customerService.findByUid("1");
    }

    @Test(expected = CustomerNotFoundException.class)
    public void findByEmail_givenUid_expectCustomerNotFoundException() {
        when(customerRepo.findCustomerByEmail(anyString())).thenReturn(Optional.empty());
        customerService.findByEmail("asndadsak");
    }

    @Test(expected = CustomerNotFoundException.class)
    public void update_givenUidAndUpdatedValues_expectCustomerNotFoundException() throws ParseException {
        CustomerDto customerDto = CustomerDto.builder()
                .uid("22")
                .email("adsa@yahoo.com")
                .name("aaa")
                .ron(22)
                .phone("0755476116")
                .build();
        when(customerRepo.findCustomerByUid(anyString())).thenReturn(Optional.empty());
        customerService.update(customerDto, "1");
    }

    @Test(expected = CustomerNotFoundException.class)
    public void delete_givenUid_expectCustomerNotFoundException() {
        when(customerRepo.findCustomerByUid(anyString())).thenReturn(Optional.empty());
        customerService.deleteByUid("1");
    }
}