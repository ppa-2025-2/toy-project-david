package com.example.demo.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

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

@Entity
@Table(name = "tickets")
public class Ticket {

    public enum Status {
        PENDENTE,
        CONCLUIDO,
        ANDAMENTO,
        CANCELADO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="criador_id", nullable = false)
    private User criador;

    @ManyToOne
    @JoinColumn(name="destinatario_id", nullable = false)
    private User destinatario;

    @ManyToOne
    @JoinColumn(name="responsavel_id", nullable = true)
    private User responsavel;

    @Column(nullable = false, length = 255)
    private String objeto;

    @Column(nullable = false, length = 255)
    private String acao;

    @Column(nullable = true, length = 255)
    private String detalhes;

    @Column(nullable = false, length = 255)
    private String local;

    @Column(name = "tickets_status", nullable = false, length = 255)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = true, length = 255)
    private String motivo;

    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created_at;

    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updated_at;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "ticket_user", 
        joinColumns = @JoinColumn(name="ticket_id"),
        inverseJoinColumns = @JoinColumn(name="user_id")
    )
    private Set<User> observadores = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getCriador() {
        return criador;
    }

    public void setCriador(User criador) {
        this.criador = criador;
    }

    public User getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(User destinatario) {
        this.destinatario = destinatario;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public Set<User> getObservadores() {
        return observadores;
    }

    public void setObservadores(Set<User> observadores) {
        this.observadores = observadores;
    }

}
