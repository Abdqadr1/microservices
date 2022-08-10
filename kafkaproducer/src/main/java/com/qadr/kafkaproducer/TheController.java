package com.qadr.kafkaproducer;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.qadr.kafkaproducer.KafkaConfig.TOPIC_NAME;

@RestController
@Slf4j
public class TheController {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/get")
    public String get(){
        return "I'm here for client 2";
    }

    @GetMapping("/send")
    public void send(@RequestBody Message message){
        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(TOPIC_NAME, message.getMessage());
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error(ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                String message = result.getProducerRecord().value();
                log.info(String.format("Message %s has been sent successfully", message));
            }
        });
    }

    @Data
    @NoArgsConstructor
    static class Message{
        private String message;
    }
}
