package com.qadr.reactiveweb.twilio;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class OTPRequest {

    private String number;
    private String name;

}
