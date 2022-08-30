package com.qadr.reactiveweb.service;

import com.qadr.reactiveweb.dao.CustomerDao;
import com.qadr.reactiveweb.dao.CustomerRepository;
import com.qadr.reactiveweb.error.CustomException;
import com.qadr.reactiveweb.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return customerRepository.findById(id)
                .switchIfEmpty(
                        Mono.error(new CustomException(
                                        HttpStatus.BAD_REQUEST,
                                        String.format("Could not find customer with id: '%s'", id)))
                );
    }

    public Mono<Customer> saveCustomer(Mono<Customer> customerMono){
        customerMono.switchIfEmpty(Mono.error(new CustomException(
                HttpStatus.BAD_REQUEST,
                "Customer is null")));
        return customerMono.flatMap(customerRepository::save);
    }

    public Flux<Customer> findAgeRange(int min, int max){
        return customerRepository.findByAgeBetween(min, max);
    }

    public Mono<Void> deleteCustomerById(String id){
        return findById(id).flatMap(customer -> customerRepository.deleteById(id));
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

    public Mono<Customer> findByUsername(String username) {
        return customerRepository.findByUsernameIgnoreCase(username)
                .switchIfEmpty(
                        Mono.error(
                                new CustomException(
                                    HttpStatus.BAD_REQUEST,
                                    "Account does not exist")
                        )
                );
    }
}
