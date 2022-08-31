package com.qadr.reactiveweb.twilio;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class TwilioService {

    @Autowired
    private TwilioConfig twilioConfig;

    public Mono<OTPResponse> getOTP(OTPRequest otpRequest){
        var from = new PhoneNumber(twilioConfig.getTrialNumber());
        var to = new PhoneNumber(otpRequest.getNumber());

        var str = """
                Dear Customer ,\040
                Your OTP is %s. Use this Passcode to complete your transaction. Thank You.
                """.formatted(generateOTP());
        OTPResponse result;

        try {
            Message message = Message.creator(to, from,str).create();

            result = new OTPResponse("Message sent", message.getStatus());
        } catch (Exception e){
            System.out.println(e.getMessage());
            result = new OTPResponse(
                    "Could not send otp to number: " + otpRequest.getNumber(),
                    Message.Status.FAILED
                );
        }
        return Mono.just(result);
    }



    private String generateOTP(){
        return new DecimalFormat("00000000")
                .format(new Random().nextInt(99999999));
    }

}
