package com.example.demo.controller;


import com.example.demo.dto.EventDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/events")
public interface EventApi {

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(path="",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all events")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    ResponseEntity<List<EventDto>> findAllEvents();

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(path="/organizer/{organizer}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get event by org")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    ResponseEntity<List<EventDto>> findEventByOrganizer(@PathVariable String organizer);


    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(path= "",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add new event")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created event"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    ResponseEntity<EventDto> addEvent(@Valid @RequestBody  EventDto eventDto);

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(path = "/{eventUid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get event by Uid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Event not found")
    })
    ResponseEntity<EventDto> findEventByUid(@PathVariable String eventUid);

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(path = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get event by Name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Event not found")
    })
    ResponseEntity<EventDto> findEventByName(@PathVariable String name);

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{eventUid}")
    @ApiOperation(value = "Delete a event by Uid")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted a event"),
            @ApiResponse(code = 404, message = "Event to be deleted not found")
    })
    ResponseEntity<?> deleteEventByUid(@PathVariable String eventUid);

    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping(path = "/{eventUid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update a event")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated a event"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Event to be updated not found")
    })
    ResponseEntity<EventDto> editEvent(@Valid  @RequestBody EventDto eventDto, @PathVariable String eventUid);

}
