package com.qadr.reactiveweb.controller;

import com.qadr.reactiveweb.model.Customer;
import com.qadr.reactiveweb.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(CustomerController.class)
class CustomerControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private CustomerService customerService;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    Flux<Customer> customers;

    @BeforeEach
    void populateCustomer() throws ParseException {
        Customer customer = new Customer();
        customer.setDob(dateFormat.parse("2000-11-09"));
        customer.setAge(21);
        customer.setName("Abd Qadr");
        customer.setHobbies(List.of("Coding", "Watch movies"));
        customers = Flux.just(customer);
    }

    @Test
    void saveCustomer() throws ParseException {
        Customer customer = new Customer();
        customer.setDob(dateFormat.parse("2000-11-09"));
        customer.setAge(21);
        customer.setName("Abd Qadr");
        customer.setHobbies(List.of("Coding", "Watch movies"));
        Mono<Customer> mono = Mono.just(customer);
        when(customerService.saveCustomer(mono)).thenReturn(mono);
        webTestClient.post().uri("/customer")
                .body(Mono.just(mono), Customer.class)
                .exchange()
                .expectStatus().isOk();
    }


    @Test
    @WithMockUser(username = "user")
    void getCustomers() {
        when(customerService.findAllCustomers()).thenReturn(customers);
        Flux<Customer> responseBody = webTestClient.get().uri("/customer")
                .exchange()
                .expectStatus().isOk()
                .returnResult(Customer.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(customers.blockLast())
                .verifyComplete();
    }

    @Test
    void getCustomerById() throws ParseException {
        Customer customer = new Customer();
        customer.setDob(dateFormat.parse("2000-11-09"));
        customer.setAge(21);
        customer.setName("Abd Qadr");
        customer.setHobbies(List.of("Coding", "Watch movies"));
        Mono<Customer> mono = Mono.just(customer);
        when(customerService.findById("11")).thenReturn(mono);
        Flux<Customer> responseBody = webTestClient.get().uri("/customer/id/11")
                .exchange()
                .expectStatus().isOk()
                .returnResult(Customer.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(customer)
                .verifyComplete();
    }

    @Test
    void getCustomerAgeInRange() {
        when(customerService.findAgeRange(11, 22)).thenReturn(customers);
        Flux<Customer> responseBody = webTestClient.get().uri("/customer/range?min=11&max=22")
                .exchange()
                .expectStatus().isOk()
                .returnResult(Customer.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(Objects.requireNonNull(customers.blockLast()))
                .verifyComplete();
    }

}