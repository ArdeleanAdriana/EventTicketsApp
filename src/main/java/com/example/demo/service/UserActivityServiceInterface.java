package com.example.demo.service;

import com.example.demo.dto.UserActivityDto;
import com.example.demo.model.UserActivity;

import java.util.List;

public interface UserActivityServiceInterface {
    String exportXML();

    List<UserActivityDto> findAll();

    Integer onlineUsers();

    List<UserActivityDto> findByCustomer(String customerEmail);

    UserActivityDto login(UserActivityDto userActivityDto);

    UserActivityDto logout(UserActivityDto userActivityDto);

    UserActivityDto getUserActivityDto(UserActivityDto userActivityDto);

    UserActivityDto mapToDTO(UserActivity userActivity);

    UserActivity mapToEntity(UserActivityDto userActivityDto);

    List<UserActivityDto> mapToDTOList(List<UserActivity> userActivities);
}
