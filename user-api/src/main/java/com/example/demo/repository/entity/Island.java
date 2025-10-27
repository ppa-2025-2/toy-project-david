package com.example.demo.repository.entity;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "islands")
public class Island extends BaseEntity {

    public enum Disposition {
        PAIR(2),
        TRIANGLE(3),
        SQUARE(4),
        RECTANGLE(6),
        CIRCULAR(8);

        private final int placements;
        
        public int getPlacements() {
            return placements;
        }

        Disposition(int placements) {
            this.placements = placements;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Disposition disposition;

    @OneToMany(mappedBy = "island", 
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY,
        orphanRemoval = true)    
    @JsonManagedReference
    private Set<Workstation> workstations = new HashSet<>();

    
    public void addWorkstation(Workstation workstation) {
        workstations.add(workstation);
        workstation.setIsland(this);
    }

    public void removeWorkstation(Workstation workstation) {
        workstations.remove(workstation);
        workstation.setIsland(null);
    }

   
    public Optional<Workstation> firstAvailableWorkstation() {
        return this.workstations.stream()
            .filter(Workstation::isAvailable)
            .findFirst();
    }

    public void assignUserToAvailableWorkstation(User user) {
        firstAvailableWorkstation()
            .ifPresentOrElse(
                ws -> ws.assignUser(user),
                () -> {
                    throw new IllegalStateException("No workstation available in island with ID: " + this.id);
                }
            );
    }

    
    public long countOccupiedWorkstations() {
        return workstations.stream()
            .filter(ws -> !ws.isAvailable())
            .count();
    }

    
    public long countAvailableWorkstations() {
        return workstations.stream()
            .filter(Workstation::isAvailable)
            .count();
    }

    
    public boolean hasAvailableWorkstations() {
        // Use anyMatch for performance. It stops searching as soon as a match is found.
        return workstations.stream().anyMatch(Workstation::isAvailable);
    }

   

    public Set<Workstation> getWorkstations() {
        return workstations;
    }

    public void setWorkstations(Set<Workstation> workstations) {
        this.workstations = workstations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Disposition getDisposition() {
        return disposition;
    }

    public void setDisposition(Disposition disposition) {
        this.disposition = disposition;
    }

   
    @Override
    public String toString() {
        return "Island{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", disposition=" + disposition +
                ", workstationCount=" + (workstations != null ? workstations.size() : 0) +
                '}';
    }
}