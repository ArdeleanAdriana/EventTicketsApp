package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("/users")
public interface UserApi {

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    ResponseEntity<List<UserDto>> findAllUsers();


    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add new user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created user"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDto);


    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Login")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Logged user"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    ResponseEntity<UserDto> loginUser(@Valid @RequestBody UserDto userDto);

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(path = "/{userUid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get user by Uid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "User not found")
    })
    ResponseEntity<UserDto> findUserByUid(@PathVariable String userUid);


    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{userUid}")
    @ApiOperation(value = "Delete a user by Uid")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted a user"),
            @ApiResponse(code = 404, message = "User to be deleted not found")
    })
    ResponseEntity<?> deleteUserByUid(@PathVariable String userUid);

    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping(path = "/{userUid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated a user"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "User to be updated not found")
    })
    ResponseEntity<UserDto> editUser(@Valid @RequestBody UserDto userDto, @PathVariable String userUid);

}
