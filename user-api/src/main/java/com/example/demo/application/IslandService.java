package com.example.demo.application;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import com.example.demo.domain.selectors.IslandSelector;
import com.example.demo.domain.exceptions.NotFoundException;
import com.example.demo.repository.IslandRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.entity.Island;


@Service
public class IslandService {

    private final IslandRepository islandRepository;
    private final UserRepository userRepository;
    private final IslandSelector islandSelector;

    public IslandService(
            IslandRepository islandRepository,
            UserRepository userRepository,
            IslandSelector islandSelector) {
        this.islandRepository = islandRepository;
        this.userRepository = userRepository;
        this.islandSelector = islandSelector;
    }

    @Transactional(readOnly = true)
    public List<Island> getAllIslands() {
        return this.islandRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Island> getIslandsWithAvailableWorkstations() {
        return this.islandRepository.findIslandsWithAvailableWorkstations();
    }
    
    @Transactional
    public void allocateAvailableWorkstation(@NonNull Integer userId) {
        
        
        final var user = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException());

        final List<Island> availableIslands = islandRepository.findIslandsWithAvailableWorkstations();

        if (availableIslands.isEmpty()) {
            throw new IllegalStateException("No available workstations found in any island.");
        }
        
        final var selectedIsland = islandSelector.selectIslandToFill(availableIslands);
        
       
        selectedIsland.assignUserToAvailableWorkstation(user);
    }
}