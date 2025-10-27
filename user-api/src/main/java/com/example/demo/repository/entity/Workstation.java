package com.example.demo.repository.entity;

import java.util.Objects;

import jakarta.persistence.Column;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "workstations")
public class Workstation extends BaseEntity {

    @Id
    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String specs;

    @ManyToOne
    @JoinColumn(name = "island_id")
    @JsonBackReference
    private Island island;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    
    public boolean isAvailable() {
        return user == null;
    }

    
    public void assignUser(User user) {
        if (!isAvailable()) {
            throw new IllegalStateException("Workstation " + this.id + " já está ocupada");
        }
        this.user = user;
    }

    
    public void releaseUser() {
        this.user = null;
    }

    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public Island getIsland() {
        return island;
    }

    public void setIsland(Island island) {
        this.island = island;
    }

    @Override
    public String toString() {
        return "Workstation{" +
                "id='" + id + '\'' +
                ", specs='" + specs + '\'' +
                ", islandId=" + (island != null ? island.getId() : "null") +
                ", userId=" + (user != null ? user.getId() : "null") +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Workstation other = (Workstation) obj;
        // Entities are equal if their business keys ('id') are equal and not null.
        return id != null && id.equals(other.id);
    }
}