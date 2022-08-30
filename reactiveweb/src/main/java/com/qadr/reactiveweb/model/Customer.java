package com.qadr.reactiveweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customers")
public class Customer{

    @Id
    private String id;
    private String name;
    private Integer age;
    private Date dob;
    private boolean enabled;
    private List<String> hobbies;
    private List<String> roles;
    private String password;

    @Indexed(unique = true)
    private String username;

    public Customer(int id, String name){
        this.id = String.valueOf(id);
        this.name = name;
    }

}
