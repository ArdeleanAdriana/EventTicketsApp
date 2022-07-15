package com.example.demo.service.impl;

import com.example.demo.dto.FavoriteDto;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.exception.FavoriteNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.model.Favorite;
import com.example.demo.repository.CustomerRepo;
import com.example.demo.repository.FavoriteRepo;
import com.example.demo.service.FavoriteServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FavoriteService implements FavoriteServiceInterface {
    private final FavoriteRepo favoriteRepo;
    private final CustomerRepo customerRepo;



    public FavoriteDto save(FavoriteDto favoriteDto) {
        List<FavoriteDto> favoriteDtos = findByCustomer(favoriteDto.getCustomer());
        boolean equal = false;

        for (FavoriteDto favoriteDto1 : favoriteDtos) {
            if (favoriteDto.getName().equals(favoriteDto1.getName()) &&
                    favoriteDto.getLocation().equals(favoriteDto1.getLocation()) &&
                    favoriteDto.getType().equals(favoriteDto1.getType())
            )
                equal = true;
        }

        if (equal == false) {
            favoriteDto.setUid(UUID.randomUUID().toString());
            return mapToDTO(favoriteRepo.save(mapToEntity(favoriteDto)));
        } else
            return null;
    }

    public List<FavoriteDto> findByCustomer(String customerUid) {
        Optional<Customer> customer = customerRepo.findCustomerByUid(customerUid);
        Customer newCustomer = new Customer();
        if (customer.isPresent()) {
            newCustomer = customer.get();
        }
        List<Favorite> favorites = favoriteRepo.findFavoriteByCustomer(newCustomer);
        return mapToDTOList(favorites);
    }

    public void deleteByUid(String uid) {
        favoriteRepo.delete(favoriteRepo.findFavoriteByUid(uid).orElseThrow(() -> new FavoriteNotFoundException(uid)));
    }

    public FavoriteDto mapToDTO(Favorite favorite) {
        return FavoriteDto.builder()
                .uid(favorite.getUid())
                .name(favorite.getName())
                .location(favorite.getLocation())
                .type(favorite.getType())
                .customer(favorite.getCustomer().getEmail())
                .build();
    }

    public Favorite mapToEntity(FavoriteDto favoriteDto) {
        return Favorite.builder()
                .uid(favoriteDto.getUid())
                .name(favoriteDto.getName())
                .location(favoriteDto.getLocation())
                .customer(customerRepo.findCustomerByUid(favoriteDto.getCustomer()).orElseThrow(() -> new CustomerNotFoundException(favoriteDto.getCustomer())))
                .type(favoriteDto.getType())
                .build();
    }

    public List<FavoriteDto> mapToDTOList(List<Favorite> favorites) {
        List<FavoriteDto> dtos = new ArrayList<>();

        for (Favorite favorite : favorites) {
            dtos.add(mapToDTO(favorite));
        }
        return dtos;
    }
}
