package com.example.demo.repository;

import com.example.demo.model.Customer;
import com.example.demo.model.Event;
import com.example.demo.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepo extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findTicketByUid(String uid);

    List<Ticket> findTicketByEventUid(String uid);

    List<Ticket> findTicketByCustomerUid(String uid);

    void deleteAllByCustomer(Customer customer);

    void deleteAllByEvent(Event event);
}