package com.example.demo.service.impl;

import com.example.demo.dto.UserDto;
import com.example.demo.exception.PasswordIncorrectException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Utilizator;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Validated
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService implements UserServiceInterface {

    private final UserRepo userRepo;
    private final ModelMapper mapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserDto save(UserDto userDto) {
        userDto.setUid(UUID.randomUUID().toString());
        String encodedPass = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodedPass);
        return mapToDTO(userRepo.save(mapToEntity(userDto)));
    }


    public List<UserDto> findAll() {
        List<Utilizator> users = userRepo.findAll();
        return mapToDTOList(users);
    }

    public UserDto update(UserDto userDto, String Uid) {
        Utilizator oldUser = userRepo.findUserByUid(Uid).orElseThrow(() -> new UserNotFoundException(Uid));
        Utilizator updatedUser = mapToEntity(userDto);
        updatedUser.setId(oldUser.getId());
        updatedUser.setUid(oldUser.getUid());
        return mapToDTO(userRepo.save(updatedUser));
    }

    public UserDto loginUser(UserDto userDto) {
        UserDto foundUser = mapToDTO(userRepo.findUserByEmail(userDto.getEmail()).orElseThrow(() -> new UserNotFoundException(userDto.getEmail())));
        if (passwordEncoder.matches(userDto.getPassword(), foundUser.getPassword())) {
            foundUser.setPassword(userDto.getPassword());
            return foundUser;
        } else {
            throw new PasswordIncorrectException(userDto.getEmail());
        }
    }

    public UserDto findByUid(String uid) {
        return mapToDTO(userRepo.findUserByUid(uid).orElseThrow(() -> new UserNotFoundException(uid)));
    }

    public void deleteByUid(String uid) {
        userRepo.delete(userRepo.findUserByUid(uid).orElseThrow(() -> new UserNotFoundException(uid)));
    }

    public UserDto mapToDTO(Utilizator user) {
        return UserDto.builder()
                .uid(user.getUid())
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .role(user.getRole())
                .build();
    }

    public Utilizator mapToEntity(UserDto userDto) {
        UserFactory userFactory = new UserFactory();
        Utilizator user = userFactory.getUserType(userDto.getRole());

        user.setEmail(userDto.getEmail());
        user.setUid(userDto.getUid());
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        user.setRole(userDto.getRole());
        return user;
    }

    public List<UserDto> mapToDTOList(List<Utilizator> users) {
        List<UserDto> dtos = users.stream().map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());
        return dtos;
    }


}
