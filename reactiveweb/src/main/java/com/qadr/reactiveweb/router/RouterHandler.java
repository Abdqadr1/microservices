package com.qadr.reactiveweb.router;

import com.qadr.reactiveweb.dao.CustomerDao;
import com.qadr.reactiveweb.error.CustomException;
import com.qadr.reactiveweb.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class RouterHandler {

    @Autowired
    private CustomerDao customerDao;

    public Mono<ServerResponse> getAllCustomer(ServerRequest request){
        long start = System.currentTimeMillis();
        Flux<?> customers = Flux.just(customerDao.getCustomers().toArray());
        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start));
        return ServerResponse.ok().body(customers, Customer.class);
    }

    public Mono<ServerResponse> getAllCustomerStream(ServerRequest request){
        long start = System.currentTimeMillis();
        Flux<Customer> customers = customerDao.getCustomerStream();
        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start));
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .bodyValue(customers);
    }

    public Mono<ServerResponse> getCustomerById(ServerRequest request){
        String id = request.pathVariable("id");
        return customerDao.getCustomerById(id)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "Could not find customer")))
                .flatMap(ServerResponse.ok()::bodyValue);
    }

}
