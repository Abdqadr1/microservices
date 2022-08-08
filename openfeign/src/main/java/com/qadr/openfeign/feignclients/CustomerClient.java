package com.qadr.openfeign.feignclients;

import com.qadr.customer.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "customer",fallbackFactory = CustomerClientCallback.class)
public interface CustomerClient {

    @GetMapping("/customer")
    List<Customer> getCustomers();

    @GetMapping("/customer/count")
    Long countAllCustomers();
}
