package com.example.demo.service.impl;

import com.example.demo.dto.UserActivityDto;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.ActivityList;
import com.example.demo.model.Role;
import com.example.demo.model.UserActivity;
import com.example.demo.model.Utilizator;
import com.example.demo.repository.UserActivityRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserActivityServiceInterface;
import com.example.demo.utils.FileExporter;
import com.example.demo.utils.XMLFileExporter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserActivityService implements UserActivityServiceInterface {
    private final UserActivityRepo userActivityRepo;
    private final UserRepo userRepo;

    public String exportXML() {
        FileExporter fileExporter;
        fileExporter = new XMLFileExporter();
        List<UserActivity> activities = userActivityRepo.findAll();
        ActivityList activityList = new ActivityList();
        activityList.setUserActivityList(activities);
        return fileExporter.exportData(activityList);
    }

    public List<UserActivityDto> findAll() {
        List<UserActivity> activities = userActivityRepo.findAll();
        return mapToDTOList(activities);
    }

    public Integer onlineUsers() {
        List<UserActivity> activities = userActivityRepo.findAll();
        int login = 0;
        int logout = 0;
        for (UserActivity userActivity : activities)
            if (userActivity.getOp().equals("Login"))
                login++;
            else
                logout++;


        return login - logout;
    }

    public List<UserActivityDto> findByCustomer(String customerEmail) {
        Optional<Utilizator> user = userRepo.findUserByEmail(customerEmail);
        Utilizator utilizator = new Utilizator();
        if (user.isPresent()) {
            utilizator = user.get();
        }
        List<UserActivity> activities = userActivityRepo.findUserActivityByUtilizator(utilizator);
        return mapToDTOList(activities);
    }

    public UserActivityDto login(UserActivityDto userActivityDto) {
        userActivityDto.setUid(UUID.randomUUID().toString());


        userActivityDto.setTimestamp(new Timestamp(System.currentTimeMillis()).toString());


        userActivityDto.setOp("Login");
        return getUserActivityDto(userActivityDto);
    }


    public UserActivityDto logout(UserActivityDto userActivityDto) {
        userActivityDto.setUid(UUID.randomUUID().toString());
        userActivityDto.setTimestamp(new Timestamp(System.currentTimeMillis()).toString());
        userActivityDto.setOp("Logout");
        return getUserActivityDto(userActivityDto);
    }

    public UserActivityDto getUserActivityDto(UserActivityDto userActivityDto) {
        Optional<Utilizator> user = userRepo.findUserByUid(userActivityDto.getUtilizat());
        Utilizator utilizator = null;
        if (user.isPresent()) {
            utilizator = user.get();
        }

        if (utilizator.getRole() == Role.Client) {
            return mapToDTO(userActivityRepo.save(mapToEntity(userActivityDto)));
        } else
            return null;
    }

    public UserActivityDto mapToDTO(UserActivity userActivity) {
        return UserActivityDto.builder()
                .uid(userActivity.getUid())
                .op(userActivity.getOp())
                .timestamp(userActivity.getTimestamp())
                .utilizat(userActivity.getUtilizator().getEmail())
                .build();
    }

    public UserActivity mapToEntity(UserActivityDto userActivityDto) {
        return UserActivity.builder()
                .uid(userActivityDto.getUid())
                .op(userActivityDto.getOp())
                .timestamp(userActivityDto.getTimestamp())
                .utilizator(userRepo.findUserByUid(userActivityDto.getUtilizat()).orElseThrow(() -> new UserNotFoundException(userActivityDto.getUtilizat())))
                .build();
    }

    public List<UserActivityDto> mapToDTOList(List<UserActivity> userActivities) {
        List<UserActivityDto> dtos = new ArrayList<>();

        for (UserActivity userActivity : userActivities) {
            dtos.add(mapToDTO(userActivity));
        }
        return dtos;
    }

}