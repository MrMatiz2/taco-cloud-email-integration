package com.example.tacocloudemailintegration.services.handler;

import com.example.tacoclouddomain.email.EmailOrder;
import com.example.tacocloudemailintegration.services.configuration.ApiProperties;
import org.springframework.integration.core.GenericHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderSubmitMessageHandler implements GenericHandler<EmailOrder> {

    private final RestTemplate rest;
    private final ApiProperties apiProps;

    public OrderSubmitMessageHandler(ApiProperties apiProps, RestTemplate restTemplateEmail) {
        this.apiProps = apiProps;
        this.rest = restTemplateEmail;
    }

    @Override
    public Object handle(EmailOrder order, MessageHeaders headers) {
        rest.postForObject(apiProps.getUrl(), order, String.class);
        return null;
    }
}
