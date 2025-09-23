package com.example.demo.models.DTOs;

import java.util.List;

import com.example.demo.models.Ticket;
import com.example.demo.models.User;

// id INTEGER PRIMARY KEY AUTOINCREMENT,
//     responsavel_id INT NOT NULL,
//     destinatario_id INT NOT NULL,
//     objeto VARCHAR(255) NOT NULL,
//     acao VARCHAR(255) NOT NULL,
//     detalhes VARCHAR(255) NULL,
//     local VARCHAR(255) NOT NULL,
//     tickets_status ENUM('concluido', 'andamento',  'cancelado') NULL,
//     motivo VARCHAR(255) NULL, 
//     created_at TIMESTAMP DEFAULT current_timestamp NOT NULL,
//     update_at TIMESTAMP DEFAULT current_timestamp NOT NULL,
//     FOREIGN KEY (destinatario_id) REFERENCES users(id),
//     FOREIGN KEY (responsavel_id) REFERENCES users(id)

public record NewTicket(
        int criadorId,
        int destinatarioId,
        String objeto,
        String acao,
        String detalhes,
        String local,
        Ticket.Status status,
        String motivo,
        List<Integer> observadores_ids
        )  {

}
