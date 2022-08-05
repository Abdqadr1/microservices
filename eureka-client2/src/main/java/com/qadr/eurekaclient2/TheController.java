package com.qadr.eurekaclient2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TheController {

    @GetMapping("/get")
    public String get(){
        return "I'm here for client 2";
    }
}
