package com.qadr.openfeign;

import com.qadr.customer.Customer;
import com.qadr.openfeign.feignclients.CustomerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class Runner implements CommandLineRunner {
    @Autowired
    private CustomerClient customerClient;

    @Override
    public void run(String... args) {
        List<Customer> customers = customerClient.getCustomers();
        System.out.println(customers);

        Long count = customerClient.countAllCustomers();
        System.out.println("Customer count: "+ count);
    }
}
