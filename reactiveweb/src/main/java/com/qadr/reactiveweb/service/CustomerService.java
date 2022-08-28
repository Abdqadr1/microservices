package com.qadr.reactiveweb.service;

import com.qadr.reactiveweb.dao.CustomerDao;
import com.qadr.reactiveweb.dao.CustomerRepository;
import com.qadr.reactiveweb.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CustomerService {
    @Autowired private CustomerDao customerDao;
    @Autowired private CustomerRepository customerRepository;

    public Flux<Customer> findAllCustomers(){
        return customerRepository.findAll();
    }

    public Mono<Customer> findById(String id){
        return customerRepository.findById(id);
    }

    public Mono<Customer> saveCustomer(Customer customerMono){

        return customerRepository.save(customerMono);
    }

    public Flux<Customer> findAgeRange(int min, int max){
        return customerRepository.findByAgeBetween(min, max);
    }

    public Mono<Void> deleteCustomerById(String id){
        return customerRepository.deleteById(id);
    }


    public List<Customer> getAllCustomer(){
        long start = System.currentTimeMillis();
        List<Customer> customers = customerDao.getCustomers();
        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start));
        return customers;
    }

    public Flux<Customer> getAllCustomerStream(){
        long start = System.currentTimeMillis();
        Flux<Customer> customers = customerDao.getCustomerStream();
        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start));
        return customers;
    }

    public Mono<Void> deleteAll() {
        return customerRepository.deleteAll();
    }
}
