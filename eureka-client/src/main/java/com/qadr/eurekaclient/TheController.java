package com.qadr.eurekaclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TheController {
    @Autowired private RestTemplate restTemplate;

    @GetMapping("/get")
    public void get(){
        System.out.println("I'm here for client 1");
    }

    @GetMapping("/")
    public String home(){
        String str = restTemplate.getForObject("http://CLIENT2/get", String.class);
        System.out.println(str);
        return "This is home";
    }

}
