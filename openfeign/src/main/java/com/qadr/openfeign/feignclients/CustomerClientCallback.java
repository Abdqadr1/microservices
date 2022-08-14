package com.qadr.openfeign.feignclients;
import com.qadr.openfeign.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * fallback factory for hystrix client
 * If one needs access to the cause that made the fallback trigger,
 * one can use the fallbackFactory attribute inside @FeignClient.
 * */
@Component
@Slf4j
public class CustomerClientCallback implements FallbackFactory<CustomerClient> {

    @Override
    public CustomerClient create(Throwable cause) {
        return new CustomerClient() {
            @Override
            public List<Customer> getCustomers() {
                log.info(cause.getMessage());
                return Collections.emptyList();
            }

            @Override
            public Long countAllCustomers() {
                log.info(cause.getMessage());
                return 0L;
            }
        };
    }
}
