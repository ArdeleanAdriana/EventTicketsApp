package com.example.demo.controller;


import com.example.demo.dto.UserActivityDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("/activities")
public interface UserActivityApi {

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(path="",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all activity")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    ResponseEntity<List<UserActivityDto>> findAllActivities();


    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(path= "/login",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add login activity")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created event"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    ResponseEntity<UserActivityDto> login(@Valid @RequestBody UserActivityDto userActivityDto);


    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(path= "/logout",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add logout activity")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created event"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    ResponseEntity<UserActivityDto> logout(@Valid @RequestBody UserActivityDto userActivityDto);

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(path="/{utilizator}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get organizer by customer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    ResponseEntity<List<UserActivityDto>> findActivityByClient(@PathVariable String utilizator);

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(path = "/online")
    @ApiOperation(value = "Get nr of connected users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    ResponseEntity<Integer> getOnlineUsers();

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(path = "/xml")
    @ApiOperation(value = "export XML")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    ResponseEntity<?> exportXML();

}
