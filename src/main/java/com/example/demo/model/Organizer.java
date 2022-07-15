package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Setter
@Getter
@NoArgsConstructor

@Entity

public class Organizer extends Utilizator implements UserType {

    public Organizer(Long id, String uid, String email, String password, Role role, String name) {
        super(id, uid, email, name, password, role);

    }

    @Override
    public void print() {

    }


}
