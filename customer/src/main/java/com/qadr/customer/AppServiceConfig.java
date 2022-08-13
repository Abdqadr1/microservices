package com.qadr.customer;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties()
@Data
public class AppServiceConfig {

    private Map<String, String> server;

    private Map<String, Object> spring;

}
