package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.demo.domain.TicketService;
import com.example.demo.dto.TicketDTO;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    private final TicketService service;

    public TicketController(TicketService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void novoTicket(@RequestBody TicketDTO dto) {
        service.create(dto.userId(), dto.tipo());
    }
}
