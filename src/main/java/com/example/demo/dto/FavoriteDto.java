package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteDto {

    private String uid;
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;
    @Size(min = 1)
    private String type;
    @Size(min = 1)
    private String location;
    @NotNull
    private String customer;
}
