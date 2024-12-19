package com.example.tacocloudemailintegration.services.handler;

import com.example.tacoclouddomain.email.EmailOrder;
import com.example.tacocloudemailintegration.services.configuration.ApiProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.integration.core.GenericHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderSubmitMessageHandler implements GenericHandler<EmailOrder> {

    Logger logger = LoggerFactory.getLogger(OrderSubmitMessageHandler.class);

    private final RestTemplate rest;
    private final ApiProperties apiProps;
    private final RestTemplate restTemplateEmail;

    public OrderSubmitMessageHandler(ApiProperties apiProps, RestTemplate restTemplateEmail) {
        this.apiProps = apiProps;
        this.rest = restTemplateEmail;
        this.restTemplateEmail = restTemplateEmail;
    }

    @Override
    public Object handle(EmailOrder order, MessageHeaders headers) {
        try {
            HttpHeaders headersHttp = new HttpHeaders();
            headersHttp.setContentType(MediaType.APPLICATION_JSON);
            ObjectMapper mapper = new ObjectMapper();
            String orderJson = mapper.writeValueAsString(order);
            HttpEntity<String> request = new HttpEntity<>(orderJson, headersHttp);
            rest.postForObject(apiProps.getUrl(), request, String.class);
        }catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
