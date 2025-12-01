package com.example.demo.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TicketClient {
    private final String ticketServiceUrl;
    private final RestTemplate api = new RestTemplate();
    
    record TicketRequest(Integer userId, String type) {}

    public TicketClient(
        @Value("${ticket.service.url}")
        String ticketServiceUrl
    ) {
        this.ticketServiceUrl = ticketServiceUrl;
    }

    public void createTicket(Integer userId, String type) {
        var body = new TicketRequest(userId, type);
        api.postForEntity(ticketServiceUrl, new HttpEntity<>(body), Void.class);
    }

}
