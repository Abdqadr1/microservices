package com.qadr.customer.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document("customers")
public class Customer {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private Gender gender;
    private List<String> favouriteCars;
    private LocalDateTime created_at;
}
