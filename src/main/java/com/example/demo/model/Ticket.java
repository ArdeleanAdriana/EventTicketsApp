package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String uid;

    @ManyToOne
    @JoinColumn(name = "customer_id",
            referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "event_id",
            referencedColumnName = "id")
    private Event event;
}
