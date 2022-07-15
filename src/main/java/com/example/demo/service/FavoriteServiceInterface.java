package com.example.demo.service;

import com.example.demo.dto.FavoriteDto;
import com.example.demo.model.Favorite;

import java.util.List;


public interface FavoriteServiceInterface {
    FavoriteDto save(FavoriteDto favoriteDto);

    List<FavoriteDto> findByCustomer(String customerUid);

    void deleteByUid(String uid);

    FavoriteDto mapToDTO(Favorite favorite);

    Favorite mapToEntity(FavoriteDto favoriteDto);

    List<FavoriteDto> mapToDTOList(List<Favorite> favorites);
}
