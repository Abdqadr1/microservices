package com.qadr.reactiveweb.controller;

import com.qadr.reactiveweb.service.CustomerService;
import com.qadr.reactiveweb.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired private CustomerService customerService;

    @GetMapping
    public Flux<Customer> getCustomers(){
        return customerService.findAllCustomers();
    }

    @GetMapping("/id/{id}")
    public Mono<Customer> getCustomerById(@PathVariable("id") String id){
        return customerService.findById(id);
    }

    @GetMapping("/range")
    public Flux<Customer> getCustomerAgeInRange(
            @RequestParam("min") Integer min,
            @RequestParam("max") Integer max
    ){
        return customerService.findAgeRange(min, max);
    }

    @PostMapping
    public Mono<Customer> saveCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteById(@PathVariable String id){
        return customerService.deleteCustomerById(id);
    }

    @DeleteMapping("/deleteall")
    public Mono<Void> deleteAll(){
        return customerService.deleteAll();
    }

}
