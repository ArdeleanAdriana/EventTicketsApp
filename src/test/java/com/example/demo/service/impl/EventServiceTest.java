package com.example.demo.service.impl;

import com.example.demo.dto.EventDto;
import com.example.demo.exception.EventNotFoundException;
import com.example.demo.model.Event;
import com.example.demo.model.Organizer;
import com.example.demo.repository.EventRepo;
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
public class EventServiceTest {
    @Mock
    ModelMapper modelMapper;
    @InjectMocks
    private EventService eventService;
    @Mock
    private EventRepo eventRepo;

    @Test
    public void update_givenUidAndUpdatedValues_expectTheUpdatedEvent() throws ParseException {


        EventDto eventDto = EventDto.builder()
                .duration("blabla")
                .name("aaa")
                .price(22)
                .nrTickets(10)
                .location("Blabla")
                .organizer("UTCN")
                .type("Nush")
                .build();


        Organizer organizer = new Organizer();
        organizer.setName("UTCN");

        Event event = Event.builder()
                .uid("22")
                .duration("blabla")
                .name("aaa")
                .price(22)
                .nrTickets(10)
                .location("Blabla")
                .organizer(organizer)
                .type("Nush")
                .build();


        when(eventRepo.findEventByUid("22")).thenReturn(Optional.of(event));
        when(eventRepo.save(any(Event.class))).thenReturn(event);

        eventDto.setUid("22");
        EventDto result = eventService.update(eventDto, "22");

        verify(eventRepo).save(any(Event.class));

        assertEquals(eventDto, result);
    }

    @Test
    public void findByUid_givenUid_expectTheEvent() throws ParseException {
        Organizer organizer = new Organizer();
        organizer.setName("UTCN");
        Event event = Event.builder()
                .uid("22")
                .duration("blabla")
                .name("aaa")
                .price(22)
                .nrTickets(10)
                .location("Blabla")
                .organizer(organizer)
                .type("Nush")
                .build();

        when(eventRepo.findEventByUid(anyString())).thenReturn(Optional.of(event));

        EventDto fetchedEmployee = eventService.findByUid(event.getUid());

        verify(eventRepo).findEventByUid("22");

        assertEquals(fetchedEmployee.getName(), event.getName());
        assertEquals(fetchedEmployee.getDuration(), event.getDuration());
        assertEquals((int) (fetchedEmployee.getPrice()), (int) (event.getPrice()));
        assertEquals(fetchedEmployee.getNrTickets(), event.getNrTickets());
        assertEquals(fetchedEmployee.getLocation(), event.getLocation());
        assertEquals(fetchedEmployee.getOrganizer(), event.getOrganizer());
        assertEquals(fetchedEmployee.getType(), event.getType());
    }

    @Test
    public void findByName_givenUid_expectTheEvent() throws ParseException {
        Organizer organizer = new Organizer();
        organizer.setName("UTCN");
        Event event = Event.builder()
                .uid("22")
                .duration("blabla")
                .name("aaa")
                .price(22)
                .nrTickets(10)
                .location("Blabla")
                .organizer(organizer)
                .type("Nush")
                .build();

        when(eventRepo.findEventByName(anyString())).thenReturn(Optional.of(event));

        EventDto fetchedEmployee = eventService.findByName(event.getName());

        verify(eventRepo).findEventByName("aaa");

        assertEquals(fetchedEmployee.getUid(), event.getUid());
        assertEquals(fetchedEmployee.getDuration(), event.getDuration());
        assertEquals((int) (fetchedEmployee.getPrice()), (int) (event.getPrice()));
        assertEquals(fetchedEmployee.getNrTickets(), event.getNrTickets());
        assertEquals(fetchedEmployee.getLocation(), event.getLocation());
        assertEquals(fetchedEmployee.getOrganizer(), event.getOrganizer());
        assertEquals(fetchedEmployee.getType(), event.getType());
    }

    @Test
    public void findByOrganizer_givenUid_expectTheEvent() throws ParseException {
        Organizer organizer = new Organizer();
        organizer.setName("UTCN");

        Event event1 = Event.builder()
                .uid("ss")
                .duration("blabla")
                .name("aaa")
                .price(22)
                .nrTickets(10)
                .location("Blabla")
                .organizer(organizer)
                .type("Nush")
                .build();

        Event event2 = Event.builder()
                .uid("aa")
                .duration("asdsadasd")
                .name("asdasdasd")
                .price(223)
                .nrTickets(20)
                .location("Blabla")
                .organizer(organizer)
                .type("adsadassda")
                .build();

        List<Event> events = Arrays.asList(event1, event2);

        when(eventRepo.findEventByOrganizer(any())).thenReturn(events);

        List<EventDto> fetchedEmployees = eventService.findByOrganizer("UTCN");

        Assertions.assertEquals(events.size(), fetchedEmployees.size());

    }

    @Test
    public void findAll_givenEvents_expectTheEvents() throws ParseException {
        Organizer organizer = new Organizer();
        organizer.setName("UTCN");
        Event event1 = Event.builder()
                .uid("ss")
                .duration("blabla")
                .name("aaa")
                .price(22)
                .nrTickets(10)
                .location("Blabla")
                .organizer(organizer)
                .type("Nush")
                .build();


        Organizer organizer2 = new Organizer();
        organizer.setName("adasasdas");
        Event event2 = Event.builder()
                .uid("aa")
                .duration("asdsadasd")
                .name("asdasdasd")
                .price(223)
                .nrTickets(20)
                .location("Blabla")
                .organizer(organizer2)
                .type("adsadassda")
                .build();


        List<Event> events = Arrays.asList(event1, event2);

        when(eventRepo.findAll()).thenReturn(events);
        List<EventDto> resultedValue = eventService.findAll();
        Assertions.assertEquals(events.size(), resultedValue.size());
    }

    @Test
    public void deleteByUid_givenUid_expectStatusOk() throws ParseException {
        Organizer organizer = new Organizer();
        organizer.setName("UTCN");
        Event event = Event.builder()
                .uid("22")
                .duration("blabla")
                .name("aaa")
                .price(22)
                .nrTickets(10)
                .location("Blabla")
                .organizer(organizer)
                .type("Nush")
                .build();
        when(eventRepo.findEventByUid(anyString())).thenReturn(Optional.of(event));

        eventService.deleteByUid(event.getUid());
        verify(eventRepo).delete(event);
    }

    @Test
    public void fromEntityToDto_givenEntity_expectDto() throws ParseException {
        Organizer organizer = new Organizer();
        organizer.setName("UTCN");
        Event event = Event.builder()
                .uid("22")
                .duration("blabla")
                .name("aaa")
                .price(22)
                .nrTickets(10)
                .location("Blabla")
                .organizer(organizer)
                .type("Nush")
                .build();

        EventDto employeeDto = eventService.mapToDTO(event);

        assertEquals(employeeDto.getName(), event.getName());
        assertEquals(employeeDto.getDuration(), event.getDuration());
        assertEquals((int) (employeeDto.getPrice()), (int) (event.getPrice()));
        assertEquals(employeeDto.getNrTickets(), event.getNrTickets());
        assertEquals(employeeDto.getLocation(), event.getLocation());
        assertEquals(employeeDto.getOrganizer(), event.getOrganizer());
        assertEquals(employeeDto.getType(), event.getType());

    }

    @Test
    public void fromDtoToEntity_givenDto_expectEntity() throws ParseException {
        EventDto eventDto = EventDto.builder()
                .uid("22")
                .duration("blabla")
                .name("aaa")
                .price(22)
                .nrTickets(10)
                .location("Blabla")
                .organizer("UTCN")
                .type("Nush")
                .build();


        Event event = eventService.mapToEntity(eventDto);

        assertEquals(eventDto.getName(), event.getName());
        assertEquals(eventDto.getDuration(), event.getDuration());
        assertEquals((int) (eventDto.getPrice()), (int) (event.getPrice()));
        assertEquals(eventDto.getNrTickets(), event.getNrTickets());
        assertEquals(eventDto.getLocation(), event.getLocation());
        assertEquals(eventDto.getOrganizer(), event.getOrganizer());
        assertEquals(eventDto.getType(), event.getType());

    }


    @Test(expected = EventNotFoundException.class)
    public void findByUid_givenUid_expectEventNotFoundException() {
        when(eventRepo.findEventByUid(anyString())).thenReturn(Optional.empty());
        eventService.findByUid("1");
    }

    @Test(expected = EventNotFoundException.class)
    public void update_givenUidAndUpdatedValues_expectEventNotFoundException() throws ParseException {
        EventDto eventDto = EventDto.builder()
                .uid("22")
                .duration("blabla")
                .name("aaa")
                .price(22)
                .nrTickets(10)
                .location("Blabla")
                .organizer("UTCN")
                .type("Nush")
                .build();
        when(eventRepo.findEventByUid(anyString())).thenReturn(Optional.empty());
        eventService.update(eventDto, "1");
    }

    @Test(expected = EventNotFoundException.class)
    public void delete_givenUid_expectEventNotFoundException() {
        when(eventRepo.findEventByUid(anyString())).thenReturn(Optional.empty());
        eventService.deleteByUid("1");
    }

}