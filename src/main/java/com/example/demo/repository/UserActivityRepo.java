package com.example.demo.repository;

import com.example.demo.model.UserActivity;
import com.example.demo.model.Utilizator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserActivityRepo extends JpaRepository<UserActivity, Long> {
    List<UserActivity> findUserActivityByUtilizator(Utilizator utilizator);
}
