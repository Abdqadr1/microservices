package com.qadr.openfeign.feignclients;

import com.qadr.customer.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "customer",
        url = "http://localhost:8085/customer", fallback = CustomerClientCallback.class)
public interface CustomerClient {

    @GetMapping
    List<Customer> getCustomers();

    @GetMapping("/count")
    Long countAllCustomers();
}
