package com.qadr.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerService {
    @Autowired private CustomerRepository repository;
    @Autowired private MongoTemplate mongoTemplate;

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

    public List<Customer> findCustomer(String keyword){
        Query query = new Query();
        Criteria criteria = new Criteria();
        query.addCriteria(
                criteria.orOperator(
//                        Criteria.where("firstName").regex(keyword),
//                        Criteria.where("lastName").regex(keyword),
//                        Criteria.where("email").regex(keyword),
                        Criteria.where("address.street").regex(keyword),
                        Criteria.where("address.city").regex(keyword),
                        Criteria.where("address.state").regex(keyword),
                        Criteria.where("address.country").regex(keyword)
                )
        );
        List<Customer> customers = mongoTemplate.find(query, Customer.class);
        System.out.println(customers);
        return customers;
    }

}
