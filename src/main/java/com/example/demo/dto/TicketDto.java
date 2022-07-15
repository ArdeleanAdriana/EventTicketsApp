package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Valid
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {
    private String uid;

    //@NotNull
    private String customerUid;
    //@NotNull
    private String customerEmail;

    //@NotNull
    private String eventUid;
   // @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String eventName;
    //@NotNull
    private String eventOrganizer;


}

