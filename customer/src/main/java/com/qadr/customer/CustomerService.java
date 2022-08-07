package com.qadr.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerService {
    @Autowired private CustomerRepository repository;

    public Customer save(Customer customer){
        customer.setCreated_at(LocalDateTime.now());
        return repository.save(customer);
    }

    public List<Customer> getAllCustomers(){
        return repository.findAll();
    }

    public Customer getCustomerById(String id){
        return repository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Customer not found"
                        ));
    }

    public Long count(){
        return repository.count();
    }

    public void deleteById(String id){
        getCustomerById(id);
        repository.deleteById(id);
    }

}
