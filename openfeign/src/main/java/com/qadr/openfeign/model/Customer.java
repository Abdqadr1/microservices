package com.qadr.openfeign.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Customer {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private Gender gender;
    private List<String> favouriteCars;
    private LocalDateTime created_at;
}
