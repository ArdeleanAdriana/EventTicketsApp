package com.example.demo.service.impl;

import com.example.demo.model.Customer;
import com.example.demo.model.Organizer;
import com.example.demo.model.Role;
import com.example.demo.model.Utilizator;

public class UserFactory {
    public Utilizator getUserType(Role role) {
        if (role == null) {
            return null;
        }
        if (role == Role.Organizer) {
            return new Organizer();

        } else if (role == Role.Client) {
            return new Customer();
        } else if (role == Role.Admin) {
            return new Utilizator();
        }

        return null;
    }
}
