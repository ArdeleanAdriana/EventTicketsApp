package com.example.demo.controller;

import com.example.demo.dto.FavoriteDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("/favorites")
public interface FavoriteApi {

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(path = "/customer/{customer}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get favorite by customer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    ResponseEntity<List<FavoriteDto>> findFavoriteByCustomer(@PathVariable String customer);


    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add new favorite")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created event"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    ResponseEntity<FavoriteDto> addFavorite(@Valid @RequestBody FavoriteDto favoriteDto);

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{favoriteUid}")
    @ApiOperation(value = "Delete a customer by Uid")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted a customer"),
            @ApiResponse(code = 404, message = "Favorite to be deleted not found")
    })
    ResponseEntity<?> deleteFavoriteByUid(@PathVariable String favoriteUid);
}
