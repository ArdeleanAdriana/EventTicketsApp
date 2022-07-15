package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.model.Utilizator;

import java.util.List;

public interface UserServiceInterface {
    UserDto save(UserDto userDto);

    List<UserDto> findAll();

    UserDto update(UserDto userDto, String Uid);

    UserDto findByUid(String uid);

    void deleteByUid(String uid);

    UserDto mapToDTO(Utilizator user);

    Utilizator mapToEntity(UserDto userDto);

    List<UserDto> mapToDTOList(List<Utilizator> users);

    UserDto loginUser(UserDto userDto);
}
