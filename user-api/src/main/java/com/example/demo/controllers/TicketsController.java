package com.example.demo.controllers;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Role;
import com.example.demo.models.Ticket;
import com.example.demo.models.User;
import com.example.demo.models.DTOs.NewTicket;
import com.example.demo.repositories.TicketRepository;
import com.example.demo.repositories.UserRepository;


@RestController
@RequestMapping("/api/v1/tickets")
public class TicketsController {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketsController(TicketRepository ticketRepository, UserRepository userRepository) { 
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Ticket> newTicket(@RequestBody NewTicket newTicket) {
        Ticket ticket  = new Ticket();
        ticket.setAcao(newTicket.acao());
        ticket.setStatus(newTicket.status());
        ticket.setDetalhes(newTicket.detalhes());
        ticket.setLocal(newTicket.local());
        ticket.setObjeto(newTicket.objeto());
        ticket.setMotivo(newTicket.motivo());
        
        Set<User> observadores = new HashSet<>(userRepository.findAllById(newTicket.observadores_ids()));
        
        System.out.println("\n\n\n\n\nPASSOU 1\n\n\n\n");

        ticket.setObservadores(observadores);

        Optional<User> destinatario = userRepository.findById(newTicket.destinatarioId());

        System.out.println("destinatario: " + 
        destinatario.get());

        ticket.setDestinatario(destinatario.get());

        System.out.println("\n\n\n\n\nPASSOU 2\n\n\n\n");

        Optional<User> criador = userRepository.findById(newTicket.criadorId());
        ticket.setCriador(criador.get());

        System.out.println("\n\n\n\n\nPASSOU 3\n\n\n\n");
        
        System.out.println(ticket);
        ticketRepository.save(ticket); 
        return ResponseEntity.ok(ticket);
    }

    // private String generateHandle(String email) {
    //     String[] parts = email.split("@");
    //     String handle = parts[0];
    //     int i = 1;
    //     while (ticketRepository.existsByHandle(handle)) {
    //         handle = parts[0] + i++;
    //     }
    //     return handle;
    // }

    // @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<List<User>> getUsers() {
    //     return ResponseEntity.ok(ticketRepository.findAll());
    // }
}
