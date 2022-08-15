package com.qadr.openfeign;
import com.qadr.openfeign.feignclients.CustomerClient;
import com.qadr.openfeign.model.Customer;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/c")
public class TheController {
    @Autowired
    private CustomerClient customerClient;
    private Integer rate = 1;

    @GetMapping
    public List<Customer> listAllCustomer(){
        List<Customer> customers = customerClient.getCustomers();
        System.out.println(customers);
        return customers;
    }

    @GetMapping("/count")
    public Long countAllCustomer(){
        Long count = customerClient.countAllCustomers();
        System.out.println("Customer count: "+ count);
        return count;
    }

    @GetMapping("/rate")
    @RateLimiter(name = "rateIt", fallbackMethod = "rateFallBack")
    public Integer rateLimiter(){
        return rate++;
    }
    public Integer rateFallBack(Throwable t){
        System.out.println("rate from fallback: " + t.getMessage());
        return 0;
    }

}
