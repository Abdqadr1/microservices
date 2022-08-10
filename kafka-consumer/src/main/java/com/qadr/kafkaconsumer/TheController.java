package com.qadr.kafkaconsumer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TheController {

    @GetMapping("/get")
    public void get(){
        System.out.println("I'm here for client 1");
    }

    @GetMapping("/")
    public void home(){
        System.out.println("This is home");
    }

}
