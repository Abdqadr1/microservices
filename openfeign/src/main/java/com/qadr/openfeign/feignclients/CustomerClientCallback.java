package com.qadr.openfeign.feignclients;

import com.qadr.customer.Customer;

import java.util.Collections;
import java.util.List;

/**
 * fallback for hystrix client
 * If one needs access to the cause that made the fallback trigger,
 * one can use the fallbackFactory attribute inside @FeignClient.
 * */
public class CustomerClientCallback implements CustomerClient{
    @Override
    public List<Customer> getCustomers() {
        return Collections.emptyList();
    }

    @Override
    public Long countAllCustomers() {
        return 0L;
    }
}
