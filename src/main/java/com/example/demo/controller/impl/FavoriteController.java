package com.example.demo.controller.impl;

import com.example.demo.controller.FavoriteApi;
import com.example.demo.dto.FavoriteDto;
import com.example.demo.service.impl.FavoriteService;
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
public class FavoriteController implements FavoriteApi {
    private final FavoriteService favoriteService;

    @Override
    public ResponseEntity<List<FavoriteDto>> findFavoriteByCustomer(String customer) {
        List<FavoriteDto> favorites = favoriteService.findByCustomer(customer);
        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<FavoriteDto> addFavorite(@Valid FavoriteDto favoriteDto) {
        return new ResponseEntity<>(favoriteService.save(favoriteDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> deleteFavoriteByUid(String favoriteUid) {
        favoriteService.deleteByUid(favoriteUid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
