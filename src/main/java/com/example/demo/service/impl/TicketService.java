package com.example.demo.service.impl;


import com.example.demo.config.FileWriterr;
import com.example.demo.dto.TicketDto;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.exception.EventNotFoundException;
import com.example.demo.exception.TicketNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.model.Event;
import com.example.demo.model.Ticket;
import com.example.demo.repository.CustomerRepo;
import com.example.demo.repository.EventRepo;
import com.example.demo.repository.TicketRepo;
import com.example.demo.service.TicketServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class TicketService implements TicketServiceInterface {

    private final TicketRepo ticketRepo;
    private final EventRepo eventRepo;
    private final CustomerRepo customerRepo;
    private final EmailSenderService senderService;


    private int order = 0;


    @Scheduled(fixedRate = 120000)
    public void scheduleFixedRateTask() {
        System.out.println(
                "Fixed rate task - ");
        List<Customer> customers = customerRepo.findAll();
        int nrCustomers = customers.size();
        List<Event> events = eventRepo.findAll();
        int nrEvents = events.size();

        Random rand = new Random();
        int rand_customer = rand.nextInt(nrCustomers);
        int rand_event = rand.nextInt(nrEvents);

        Ticket ticket = new Ticket();
        ticket.setCustomer(customers.get(rand_customer));
        ticket.setEvent(events.get(rand_event));
        ticket.setUid(UUID.randomUUID().toString());

        ticketRepo.save(ticket);
    }


    public TicketDto save(TicketDto ticketDto) {
        ticketDto.setUid(UUID.randomUUID().toString());
        TicketDto ticket = mapToDTO(ticketRepo.save(mapToEntity(ticketDto)));
        Optional<Customer> customer = customerRepo.findCustomerByUid(ticket.getCustomerUid());
        Optional<Event> event = eventRepo.findEventByUid(ticket.getEventUid());
        Customer newCustomer = new Customer();
        Event newEvent = new Event();
        if (customer.isPresent()) {
            newCustomer = customer.get();


        }
        if (event.isPresent()) {
            newEvent = event.get();
        }
        newCustomer.setRon(newCustomer.getRon() - newEvent.getPrice());
        newEvent.setNrTickets(newEvent.getNrTickets() - 1);
        if (newCustomer.getRon() >= 0 && newEvent.getNrTickets() >= 0) {
            customerRepo.save(newCustomer);
            eventRepo.save(newEvent);
        }

        order++;
        String s = "";

        s = s + "Chitanta pentru ticket-ul " + ticket.getUid() + " pentru evenimentul "
                + event.get().getName() + " cu pretul " + event.get().getPrice()
                + " la locatia " + event.get().getLocation()
                + " pentru clientul  " + customer.get().getName();
        FileWriterr fw = new FileWriterr(s, order);

        log.info("sending email");
        senderService.sendSimpleEmail(newCustomer.getEmail(), s, "Chitanta");

        return ticket;
    }


    public List<TicketDto> findAll() {
        List<Ticket> tickets = ticketRepo.findAll();
        return mapToDTOList(tickets);

    }

    public List<TicketDto> findTicketByCustomerUid(String uid) {
        List<Ticket> tickets = ticketRepo.findTicketByCustomerUid(uid);
        return mapToDTOList(tickets);

    }

    public List<TicketDto> findTicketByEventUid(String uid) {
        List<Ticket> tickets = ticketRepo.findTicketByEventUid(uid);
        return mapToDTOList(tickets);

    }


    public TicketDto update(TicketDto ticketDto, String Uid) {
        Ticket oldTicket = ticketRepo.findTicketByUid(Uid).orElseThrow(() -> new TicketNotFoundException(Uid));
        Ticket updatedTicket = mapToEntity(ticketDto);
        updatedTicket.setId(oldTicket.getId());
        updatedTicket.setUid(oldTicket.getUid());
        return mapToDTO(ticketRepo.save(updatedTicket));
    }

    public TicketDto findByUid(String uid) {
        return mapToDTO(ticketRepo.findTicketByUid(uid).orElseThrow(() -> new TicketNotFoundException(uid)));
    }

    public void deleteByUid(String uid) {
        ticketRepo.delete(ticketRepo.findTicketByUid(uid).orElseThrow(() -> new TicketNotFoundException(uid)));
    }


    public TicketDto mapToDTO(Ticket ticket) {
        return TicketDto.builder()
                .customerUid(ticket.getCustomer().getUid())
                .eventUid(ticket.getEvent().getUid())
                .eventName(ticket.getEvent().getName())
                .customerEmail(ticket.getCustomer().getEmail())
                .eventOrganizer(ticket.getEvent().getOrganizer().getName())
                .uid(ticket.getUid())
                .build();
    }

    public Ticket mapToEntity(TicketDto ticketDto) {
        return Ticket.builder()
                .uid(ticketDto.getUid())
                .customer(customerRepo.findCustomerByUid(ticketDto.getCustomerUid()).orElseThrow(() -> new CustomerNotFoundException(ticketDto.getCustomerUid())))
                .event(eventRepo.findEventByUid(ticketDto.getEventUid()).orElseThrow(() -> new EventNotFoundException(ticketDto.getEventUid())))

                .build();
    }

    public List<TicketDto> mapToDTOList(List<Ticket> tickets) {
        List<TicketDto> dtos = new ArrayList<>();

        for (Ticket ticket : tickets) {
            dtos.add(mapToDTO(ticket));
        }
        return dtos;
    }
}