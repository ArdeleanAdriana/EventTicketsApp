package com.example.demo.controller.impl;

import com.example.demo.controller.TicketApi;
import com.example.demo.dto.TicketDto;
import com.example.demo.service.impl.TicketService;
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
public class TicketController implements TicketApi {
    private final TicketService ticketService;

    @Override
    public ResponseEntity<List<TicketDto>> findAllTickets() {
        List<TicketDto> tickets = ticketService.findAll();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TicketDto>> findTicketByEventUid(String eventUid) {
        List<TicketDto> tickets = ticketService.findTicketByEventUid(eventUid);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TicketDto>> findTicketByCustomerUid(String customerUid) {
        List<TicketDto> tickets = ticketService.findTicketByCustomerUid(customerUid);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TicketDto> addTicket(@Valid TicketDto ticketDto) {
        return new ResponseEntity<>(ticketService.save(ticketDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TicketDto> findTicketByUid(String ticketUid) {
        return new ResponseEntity<>(ticketService.findByUid(ticketUid), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteTicketByUid(@PathVariable String ticketUid) {
        ticketService.deleteByUid(ticketUid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TicketDto> editTicket(@Valid TicketDto ticketDto, String ticketUid) {
        return new ResponseEntity<>(ticketService.update(ticketDto, ticketUid), HttpStatus.OK);
    }
}
