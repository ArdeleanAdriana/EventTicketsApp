package com.example.demo.controller.impl;


import com.example.demo.controller.EventApi;
import com.example.demo.dto.EventDto;
import com.example.demo.service.impl.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@CrossOrigin
public class EventController implements EventApi {
    private final EventService eventService;

    @Override
    public ResponseEntity<List<EventDto>> findAllEvents() {

        List<EventDto> eventDtos = eventService.findAll();
        return new ResponseEntity<>(eventDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EventDto>> findEventByOrganizer(String organizer) {

        List<EventDto> eventDtos = eventService.findByOrganizer(organizer);
        return new ResponseEntity<>(eventDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EventDto> addEvent(@Valid @RequestBody EventDto eventDto) {
        return new ResponseEntity<>(eventService.save(eventDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<EventDto> findEventByUid(String eventUid) {
        return new ResponseEntity<>(eventService.findByUid(eventUid), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EventDto> findEventByName(String name) {
        return new ResponseEntity<>(eventService.findByName(name), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteEventByUid(@PathVariable String eventUid) {
        eventService.deleteByUid(eventUid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EventDto> editEvent(@Valid EventDto eventDto, String eventUid) {
        return new ResponseEntity<>(eventService.update(eventDto, eventUid), HttpStatus.OK);
    }
}
