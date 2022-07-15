package com.example.demo.service.impl;


import com.example.demo.dto.EventDto;
import com.example.demo.exception.EventNotFoundException;
import com.example.demo.exception.OrganizerNotFoundException;
import com.example.demo.model.Event;
import com.example.demo.model.Organizer;
import com.example.demo.repository.EventRepo;
import com.example.demo.repository.OrganizerRepo;
import com.example.demo.repository.TicketRepo;
import com.example.demo.service.EventServiceInterface;
import com.example.demo.utils.NotificationEndPoints;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventService implements EventServiceInterface {
    private final EventRepo eventRepo;
    private final OrganizerRepo organizerRepo;
    private final TicketRepo ticketRepo;
    private final SimpMessagingTemplate template;

    public EventDto save(EventDto eventDto) {
        eventDto.setUid(UUID.randomUUID().toString());
        this.template.convertAndSend(NotificationEndPoints.EventAdded,
                "A new event has been added with name: " + eventDto.getName() + " and location " + eventDto.getLocation());
        return mapToDTO(eventRepo.save(mapToEntity(eventDto)));
    }


    public List<EventDto> findAllWithFilter(List<String> locations, List<String> types, int minPrice, int maxPrice) {
        List<Event> initialList = eventRepo.findAll();
        List<Event> finalList = new ArrayList<>();
        for (Event event : initialList) {
            if (locations.contains(event.getLocation()))
                if (types.contains(event.getType()))
                    if (minPrice <= event.getPrice() && maxPrice >= event.getPrice())
                        finalList.add(event);
        }
        return mapToDTOList(finalList);
    }


    public List<EventDto> findAll() {
        List<Event> students = eventRepo.findAll();
        return mapToDTOList(students);
    }

    public List<EventDto> findByOrganizer(String organizerUid) {
        Optional<Organizer> organizer = organizerRepo.findOrganizerByUid(organizerUid);
        Organizer newOrganizer = new Organizer();
        if (organizer.isPresent()) {
            newOrganizer = organizer.get();
        }
        List<Event> students = eventRepo.findEventByOrganizer(newOrganizer);
        return mapToDTOList(students);
    }

    public EventDto update(EventDto eventDto, String Uid) {
        Event oldEvent = eventRepo.findEventByUid(Uid).orElseThrow(() -> new EventNotFoundException(Uid));
        Event updatedEvent = mapToEntity(eventDto);
        updatedEvent.setId(oldEvent.getId());
        updatedEvent.setUid(oldEvent.getUid());
        return mapToDTO(eventRepo.save(updatedEvent));
    }

    public EventDto findByUid(String uid) {
        return mapToDTO(eventRepo.findEventByUid(uid).orElseThrow(() -> new EventNotFoundException(uid)));
    }

    public EventDto findByName(String name) {
        return mapToDTO(eventRepo.findEventByName(name).orElseThrow(() -> new EventNotFoundException(name)));
    }

    @Transactional
    public void deleteByUid(String uid) {
        Event event = eventRepo.findEventByUid(uid).orElseThrow(() -> new EventNotFoundException(uid));
        ticketRepo.deleteAllByEvent(event);
        eventRepo.delete(eventRepo.findEventByUid(uid).orElseThrow(() -> new EventNotFoundException(uid)));
    }


    public EventDto mapToDTO(Event event) {
        return EventDto.builder()
                .uid(event.getUid())
                .name(event.getName())
                .location(event.getLocation())
                .organizer(event.getOrganizer().getEmail())
                .duration(event.getDuration())
                .nrTickets(event.getNrTickets())
                .price(event.getPrice())
                .type(event.getType())
                .build();
    }

    public Event mapToEntity(EventDto eventDto) {
        return Event.builder()
                .uid(eventDto.getUid())
                .name(eventDto.getName())
                .location(eventDto.getLocation())
                .organizer(organizerRepo.findOrganizerByEmail(eventDto.getOrganizer()).orElseThrow(() -> new OrganizerNotFoundException(eventDto.getOrganizer())))
                .duration(eventDto.getDuration())
                .nrTickets(eventDto.getNrTickets())
                .price(eventDto.getPrice())
                .type(eventDto.getType())
                .build();
    }

    public List<EventDto> mapToDTOList(List<Event> events) {
        List<EventDto> dtos = new ArrayList<>();

        for (Event event : events) {
            dtos.add(mapToDTO(event));
        }
        return dtos;
    }
}
