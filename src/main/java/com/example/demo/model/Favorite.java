package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String uid;
    @NotNull
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;
    @Size(min = 1)
    private String type;
    @Size(min = 1)
    private String location;

    @ManyToOne
    @JoinColumn(name = "customer_id",
            referencedColumnName = "id")
    private Customer customer;
}
