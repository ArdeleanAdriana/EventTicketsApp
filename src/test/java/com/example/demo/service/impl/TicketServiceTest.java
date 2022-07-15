package com.example.demo.service.impl;

import com.example.demo.dto.TicketDto;
import com.example.demo.exception.TicketNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.model.Event;
import com.example.demo.model.Ticket;
import com.example.demo.repository.CustomerRepo;
import com.example.demo.repository.EventRepo;
import com.example.demo.repository.TicketRepo;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

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
public class TicketServiceTest {

    @Mock
    ModelMapper modelMapper;
    @InjectMocks
    private TicketService ticketService;
    @Mock
    private TicketRepo ticketRepo;
    @Mock
    private CustomerRepo customerRepo;
    @Mock
    private EventRepo eventRepo;

    @Test
    public void save_givenTicket_expectTheTicket() throws ParseException {
        Customer customer = new Customer();
        Event event = new Event();

        customer.setUid("123");
        event.setUid("123");

        TicketDto ticketDto = TicketDto.builder()
                .customerUid("123")
                .eventUid("123")
                .build();

        Ticket ticket = Ticket.builder()
                .customer(customer)
                .event(event)
                .build();

        when(customerRepo.findCustomerByUid(anyString())).thenReturn(Optional.of(customer));
        when(eventRepo.findEventByUid(anyString())).thenReturn(Optional.of(event));
        when(ticketRepo.save(any(Ticket.class))).thenReturn(ticket);

        ticketService.save(ticketDto);

        verify(ticketRepo).save(any(Ticket.class));

    }

    @Test
    public void findByUid_givenUid_expectTheTicket() throws ParseException {
        Customer customer = new Customer();
        Event event = new Event();

        customer.setUid("123");
        event.setUid("123");

        Ticket ticket = Ticket.builder()
                .uid("22")
                .customer(customer)
                .event(event)
                .build();

        when(ticketRepo.findTicketByUid(anyString())).thenReturn(Optional.of(ticket));

        TicketDto fetchedTicket = ticketService.findByUid(ticket.getUid());

        verify(ticketRepo).findTicketByUid("22");

        assertEquals(fetchedTicket.getCustomerUid(), ticket.getCustomer().getUid());
        assertEquals(fetchedTicket.getEventUid(), ticket.getEvent().getUid());

    }

    @Test
    public void update_givenUidAndUpdatedValues_expectTheUpdatedTicket() throws ParseException {
        Customer customer = new Customer();
        Event event = new Event();

        customer.setUid("123");
        event.setUid("123");

        TicketDto ticketDto = TicketDto.builder()
                .customerUid("123")
                .eventUid("123")
                .build();

        Ticket ticket = Ticket.builder()
                .customer(customer)
                .event(event)
                .build();

        when(customerRepo.findCustomerByUid(anyString())).thenReturn(Optional.of(customer));
        when(eventRepo.findEventByUid(anyString())).thenReturn(Optional.of(event));
        when(ticketRepo.findTicketByUid(anyString())).thenReturn(Optional.of(ticket));
        when(ticketRepo.save(any(Ticket.class))).thenReturn(ticket);

        ticketService.update(ticketDto, "1234");

        verify(ticketRepo).save(any(Ticket.class));
    }

    @Test
    public void deleteByUid_givenUid() throws ParseException {
        Customer customer = new Customer();
        Event event = new Event();

        customer.setUid("123");
        event.setUid("123");


        Ticket ticket = Ticket.builder()
                .uid("1234")
                .customer(customer)
                .event(event)
                .build();


        when(ticketRepo.findTicketByUid(anyString())).thenReturn(Optional.of(ticket));

        ticketService.deleteByUid("1234");
        verify(ticketRepo).delete(ticket);
    }

    @Test(expected = TicketNotFoundException.class)
    public void update_givenUidAndUpdatedValues_expectTicketNotFoundException() throws ParseException {
        TicketDto ticketDto = TicketDto.builder()
                .uid("1234")
                .customerUid("123")
                .eventUid("123")
                .build();
        when(ticketRepo.findTicketByUid(anyString())).thenReturn(Optional.empty());
        ticketService.update(ticketDto, "1");
    }

    @Test(expected = TicketNotFoundException.class)
    public void delete_givenUidAndUpdatedValues_expectAssignmentNotFoundException() {
        when(ticketRepo.findTicketByUid(anyString())).thenReturn(Optional.empty());
        ticketService.deleteByUid("1");
    }

    @Test
    public void findAll_givenEvents_expectTheEvents() throws ParseException {
        Customer customer = new Customer();
        Event event = new Event();
        Customer customer1 = new Customer();
        Event event1 = new Event();

        customer.setUid("123");
        event.setUid("123");
        customer1.setUid("1123");
        event1.setUid("1213");

        Ticket ticket1 = Ticket.builder()
                .uid("1234")
                .customer(customer)
                .event(event)
                .build();
        Ticket ticket2 = Ticket.builder()
                .uid("12234")
                .customer(customer1)
                .event(event1)
                .build();


        List<Ticket> tickets = Arrays.asList(ticket1, ticket2);

        when(ticketRepo.findAll()).thenReturn(tickets);
        List<TicketDto> resultedValue = ticketService.findAll();
        Assertions.assertEquals(tickets.size(), resultedValue.size());
    }

    @Test
    public void findByEventUid_givenUid_expectTheTicket() throws ParseException {


        Customer customer = new Customer();
        Event event = new Event();
        Customer customer1 = new Customer();


        customer.setUid("123");
        event.setUid("123");
        customer1.setUid("1123");

        Ticket ticket1 = Ticket.builder()
                .uid("1234")
                .customer(customer)
                .event(event)
                .build();
        Ticket ticket2 = Ticket.builder()
                .uid("12234")
                .customer(customer1)
                .event(event)
                .build();

        List<Ticket> tickets = Arrays.asList(ticket1, ticket2);

        when(ticketRepo.findTicketByEventUid(anyString())).thenReturn(tickets);

        List<TicketDto> fetchedEmployees = ticketService.findTicketByEventUid("123");

        Assertions.assertEquals(tickets.size(), fetchedEmployees.size());

    }

    @Test
    public void findByCustomerUid_givenUid_expectTheTicket() throws ParseException {


        Customer customer = new Customer();
        Event event = new Event();
        Event event1 = new Event();


        customer.setUid("123");
        event.setUid("123");
        event1.setUid("1123");

        Ticket ticket1 = Ticket.builder()
                .uid("1234")
                .customer(customer)
                .event(event)
                .build();
        Ticket ticket2 = Ticket.builder()
                .uid("12234")
                .customer(customer)
                .event(event1)
                .build();

        List<Ticket> tickets = Arrays.asList(ticket1, ticket2);

        when(ticketRepo.findTicketByCustomerUid(anyString())).thenReturn(tickets);

        List<TicketDto> fetchedEmployees = ticketService.findTicketByCustomerUid("123");

        Assertions.assertEquals(tickets.size(), fetchedEmployees.size());

    }


}