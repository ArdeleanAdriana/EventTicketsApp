package com.example.demo.controller.impl;


import com.example.demo.controller.UserActivityApi;
import com.example.demo.dto.UserActivityDto;
import com.example.demo.service.impl.UserActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@CrossOrigin
public class UserActivityController implements UserActivityApi {
    private final UserActivityService userActivityService;

    @Override
    public ResponseEntity<List<UserActivityDto>> findAllActivities() {
        List<UserActivityDto> dtos = userActivityService.findAll();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserActivityDto> login(@Valid UserActivityDto userActivityDto) {
        return new ResponseEntity<>(userActivityService.login(userActivityDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserActivityDto> logout(@Valid UserActivityDto userActivityDto) {
        return new ResponseEntity<>(userActivityService.logout(userActivityDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<UserActivityDto>> findActivityByClient(String utilizator) {
        List<UserActivityDto> dtos = userActivityService.findByCustomer(utilizator);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> getOnlineUsers() {
        int nr = userActivityService.onlineUsers();
        return new ResponseEntity<Integer>(nr, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> exportXML() {
        userActivityService.exportXML();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
