package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer extends Utilizator implements UserType{


    private double ron;
    private String phone;

    @OneToMany(mappedBy = "customer")
    Set<Ticket> tickets;

    public Customer(Long id, String uid, String email, String password, Role role, String name, double ron, String phone) {
        super(id, uid, email,name, password, role);

        this.ron = ron;
        this.phone = phone;
    }

    @Override
    public void print() {

    }
}
