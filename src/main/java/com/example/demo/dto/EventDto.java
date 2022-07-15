package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class EventDto {
    private String uid;
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;
    @NotNull
    @DecimalMin(value = "0.0", message = "price should be at least 0")
    private double price;
    @Size(min = 1)
    private String duration;
    @Size(min = 1)
    private String organizer;
    @Size(min = 1)
    private String type;
    @Size(min = 1)
    private String location;
    @Min(0)
    private int nrTickets;
}

