package com.example.demo.repositories;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.ListCrudRepository;

import com.example.demo.models.Ticket;

public interface TicketRepository extends ListCrudRepository<Ticket, Integer> {

    // Optional<Ticket> findById(String id);

    // Ticket findByResponsavel(String responsavel);

    // Set<Ticket> findByIdIn(Collection<String> ids);

    // Set<Ticket> findByResponsavelIn(Collection<String> responsaveis);

    // boolean existsById(Integer id);

    // boolean existsByResponsavel(String responsavel);
    
}
