package com.qadr.reactiveweb.twilio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
public class TwilioRouterFunction {

    @Autowired
    private TwilioHandler handler;


    @Bean
    public RouterFunction<ServerResponse> routes(){
        return RouterFunctions.route()
                .GET("/router/sendOTP", handler::sendOTP)
                .build();
    }
}
