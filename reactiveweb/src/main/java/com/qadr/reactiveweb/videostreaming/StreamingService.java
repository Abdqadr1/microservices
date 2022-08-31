package com.qadr.reactiveweb.videostreaming;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Mono;

@Service
public class StreamingService {

    public static final String VIDEO_PREFIX = "classpath:video/%s.mp4";

    @Autowired
    private ResourceLoader resourceLoader;

    public Mono<Resource> getVideo(@PathVariable String name){
        return Mono.fromSupplier(()->  resourceLoader.getResource(String.format(VIDEO_PREFIX, name)));
    }
}
