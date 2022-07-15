package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Event {
    @OneToMany(mappedBy = "event")
    Set<Ticket> tickets;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(nullable = false, unique = true, updatable = false)
    private String uid;
    @NotNull
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;
    @NotNull
    @DecimalMin(value = "0.0", message = "price should be at least 0")
    private double price;
    @Size(min = 1)
    private String duration;
    private Date startDate;
    @Size(min = 1)
    private String type;
    @Size(min = 1)
    private String location;
    @Min(0)
    private int nrTickets;
    @ManyToOne
    @JoinColumn(name = "organizer_id",
            referencedColumnName = "id")
    private Organizer organizer;


}
