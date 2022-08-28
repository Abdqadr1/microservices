package com.qadr.reactiveweb.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Autowired private RouterHandler routerHandler;

    @Bean
    public RouterFunction<ServerResponse>  routerFunction(){
        return RouterFunctions.route()
                .GET("/router/customer",  routerHandler::getAllCustomer)
                .GET("/router/customer/stream", routerHandler::getAllCustomerStream)
                .build();
    }
}
