package com.qadr.reactiveweb.twilio;

import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class TwilioHandler {

    @Autowired
    private TwilioService twilioService;


    public Mono<ServerResponse> sendOTP(ServerRequest serverRequest){
        return serverRequest.bodyToMono(OTPRequest.class)
                .flatMap(twilioService::getOTP)
                .flatMap(response -> {
                    HttpStatus status =
                            response.getStatus().equals(Message.Status.DELIVERED)
                            ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
                    return ServerResponse.status(status)
                            .bodyValue(response);
                });
    }




}
