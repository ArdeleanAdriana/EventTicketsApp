package com.example.demo.controller.impl;

import com.example.demo.controller.UserApi;
import com.example.demo.dto.UserDto;
import com.example.demo.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@CrossOrigin
public class UserController implements UserApi {
    private final UserService userService;

    @Override
    public ResponseEntity<List<UserDto>> findAllUsers() {
        List<UserDto> userDtos = userService.findAll();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> addUser(@Valid UserDto userDto) {
        return new ResponseEntity<>(userService.save(userDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserDto> loginUser(@Valid UserDto userDto) {
        return new ResponseEntity<>(userService.loginUser(userDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> findUserByUid(String userUid) {
        return new ResponseEntity<>(userService.findByUid(userUid), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<?> deleteUserByUid(@PathVariable String userUid) {
        userService.deleteByUid(userUid);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Override
    public ResponseEntity<UserDto> editUser(@Valid UserDto userDto, String userUid) {
        return new ResponseEntity<>(userService.update(userDto, userUid), HttpStatus.OK);
    }
}
