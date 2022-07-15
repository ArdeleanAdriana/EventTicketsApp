package com.example.demo.service;

import com.example.demo.dto.EventDto;
import com.example.demo.model.Event;

import java.util.List;


public interface EventServiceInterface {
    EventDto save(EventDto eventDto);

    List<EventDto> findAll();

    List<EventDto> findByOrganizer(String organizer);

    EventDto update(EventDto eventDto, String Uid);

    EventDto findByUid(String uid);

    EventDto findByName(String name);

    void deleteByUid(String uid);

    EventDto mapToDTO(Event event);

    Event mapToEntity(EventDto eventDto);

    List<EventDto> mapToDTOList(List<Event> events);
}
