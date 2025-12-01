package com.example.demo.domain;

import org.springframework.stereotype.Service;

import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.entity.Ticket;

@Service
public class TicketService {

    private final TicketRepository repo;

    public TicketService(TicketRepository repo) {
        this.repo = repo;
    }

    public void create(Integer userId, String tipo) {
        Ticket newTicket = new Ticket();
        newTicket.setUserId(userId);
        newTicket.setType(tipo);
        repo.save(newTicket);
    }
}
