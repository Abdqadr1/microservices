package com.qadr.reactiveweb.dao;

import com.qadr.reactiveweb.model.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
    Flux<Customer> findByAgeBetween(int min, int max);

    Mono<Customer> findByUsernameIgnoreCase(String username);
}
