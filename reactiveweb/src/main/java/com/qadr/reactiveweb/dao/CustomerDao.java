package com.qadr.reactiveweb.dao;

import com.qadr.reactiveweb.model.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {
    private static<T> void sleep(T t) {
        try{
            Thread.sleep(1000);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public List<Customer> getCustomers(){
        return IntStream.range(1, 10)
                .peek(CustomerDao::sleep)
                .peek(i -> System.out.println("Customer " + i))
                .mapToObj(i -> new Customer(i, "Customer "+i))
                .collect(Collectors.toList());
    }

    public Flux<Customer> getCustomerStream(){
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("Customer " + i))
                .map(i -> new Customer(i, "customer "+ i));
    }

    public Mono<Customer> getCustomerById(String id){
        return Flux.range(1, 10)
                .map(i -> new Customer(i, "customer "+ i))
                .filter(customer -> customer.getId().equals(id))
                .next();
    }


}
