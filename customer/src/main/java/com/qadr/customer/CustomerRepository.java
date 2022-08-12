package com.qadr.customer;

import com.qadr.sharedlibrary.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

}
