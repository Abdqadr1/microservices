package com.qadr.reactiveweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customers")
public class Customer {

    @Id
    private String id;
    private String name;
    private Integer age;
    private Date dob;
    private boolean enabled;
    private List<String> hobbies;

    public Customer(int id, String name){
        this.id = String.valueOf(id);
        this.name = name;
    }
}
