package com.qadr.customer;

import com.qadr.sharedlibrary.Address;
import com.qadr.sharedlibrary.Customer;
import com.qadr.sharedlibrary.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDateTime;
import java.util.List;

//@Component
public class Runner implements CommandLineRunner {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void run(String... args) {
        Customer customer = new Customer();
        customer.setFirstName("Abd");
        customer.setLastName("Qadr");
        customer.setEmail("abdqadr@gmail.com");
        customer.setGender(Gender.MALE);
        customer.setCreated_at(LocalDateTime.now());
        customer.setFavouriteCars(List.of("Nissan", "Hyundai", "Pigueot", "Golf"));
        Address address = new Address(
                "Eleyele",
                "Ibadan", "Oyo state", "Nigeria"
        );
        customer.setAddress(address);
        Customer save = customerRepository.insert(customer);
        System.out.println(save);
    }
}
