package com.example.demo.service;

import com.example.demo.dto.TicketDto;
import com.example.demo.model.Ticket;

import java.util.List;


public interface TicketServiceInterface {
    TicketDto save(TicketDto ticketDto);

    List<TicketDto> findAll();

    List<TicketDto> findTicketByEventUid(String uid);

    List<TicketDto> findTicketByCustomerUid(String uid);

    TicketDto update(TicketDto ticketDto, String Uid);

    TicketDto findByUid(String uid);

    void deleteByUid(String uid);

    TicketDto mapToDTO(Ticket ticket);

    Ticket mapToEntity(TicketDto ticketDto);

    List<TicketDto> mapToDTOList(List<Ticket> tickets);
}
