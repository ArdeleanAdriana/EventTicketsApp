package com.example.demo.repository;

import com.example.demo.model.Customer;
import com.example.demo.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepo extends JpaRepository<Favorite, Long> {
    Optional<Favorite> findFavoriteByUid(String uid);

    List<Favorite> findFavoriteByCustomer(Customer customer);
}
