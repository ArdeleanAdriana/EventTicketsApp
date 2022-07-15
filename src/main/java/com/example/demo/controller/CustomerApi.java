package com.example.demo.controller;

import com.example.demo.dto.CustomerDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("/customers")
public interface CustomerApi {

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all customers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    ResponseEntity<List<CustomerDto>> findAllCustomers();


    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(path = "/{customerUid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get customer by Uid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Customer not found")
    })
    ResponseEntity<CustomerDto> findCustomerByUid(@PathVariable String customerUid);

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(path = "/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get customer by Email")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Customer not found")
    })
    ResponseEntity<CustomerDto> findCustomerByEmail(@PathVariable String email);

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{customerUid}")
    @ApiOperation(value = "Delete a customer by Uid")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted a customer"),
            @ApiResponse(code = 404, message = "Customer to be deleted not found")
    })
    ResponseEntity<?> deleteCustomerByUid(@PathVariable String customerUid);

    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping(path = "/{customerUid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update a customer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated a customer"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Customer to be updated not found")
    })
    ResponseEntity<CustomerDto> editCustomer(@Valid @RequestBody CustomerDto customerDto, @PathVariable String customerUid);

}
