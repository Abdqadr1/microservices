package com.qadr.reactiveweb.videostreaming;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class VideoController {

    @Autowired
    private StreamingService streamingService;


    @GetMapping(value = "/video/{name}", produces = "video/mp4")
    public Mono<Resource> getVideo(@PathVariable String name,
                                   @RequestHeader("Range") String range){
        System.out.println("range from " + range);
        return streamingService.getVideo(name);
    }





}
