package com.qadr.openfeign;
import com.qadr.openfeign.feignclients.CustomerClient;
import com.qadr.openfeign.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/c")
public class TheController {
    @Autowired
    private CustomerClient customerClient;
    
    @GetMapping
    public List<Customer> listAllCustomer(){
        List<Customer> customers = customerClient.getCustomers();
        System.out.println(customers);
        return customers;
    }

    @GetMapping("/count")
    public Long countAllCustomer(){
        Long count = customerClient.countAllCustomers();
        System.out.println("Customer count: "+ count);
        return count;
    }
}
