package com.example.demo.controller;


import com.example.demo.dto.TicketDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/tickets")
public interface TicketApi {

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all tickets")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    ResponseEntity<List<TicketDto>> findAllTickets( );

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(path = "/eventUid/{eventUid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all tickets")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    ResponseEntity<List<TicketDto>> findTicketByEventUid(@PathVariable  String eventUid );

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(path = "/customerUid/{customerUid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all tickets customer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    ResponseEntity<List<TicketDto>> findTicketByCustomerUid(@PathVariable  String customerUid );

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(path = "",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add new ticket")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created ticket"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    ResponseEntity<TicketDto> addTicket(@Valid @RequestBody TicketDto ticketDto);

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(path = "/{ticketUid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get ticket by Uid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Ticket not found")
    })
    ResponseEntity<TicketDto> findTicketByUid(@PathVariable String ticketUid);

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{ticketUid}")
    @ApiOperation(value = "Delete a ticket by Uid")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted a ticket"),
            @ApiResponse(code = 404, message = "Ticket to be deleted not found")
    })
    ResponseEntity<?> deleteTicketByUid(@PathVariable String ticketUid);

    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping(path = "/{ticketUid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update a ticket")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated a ticket"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Ticket to be updated not found")
    })
    ResponseEntity<TicketDto> editTicket(@Valid @RequestBody TicketDto ticketDto, @PathVariable String ticketUid);

}
