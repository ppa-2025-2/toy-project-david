package com.example.demo.application;

import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.example.demo.domain.exceptions.NotFoundException;
import com.example.demo.domain.services.IslandDomainService;
import com.example.demo.repository.IslandRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.entity.Island;

import jakarta.transaction.Transactional;

@Service
public class IslandService {
     private final IslandRepository islandRepository;
    private final UserRepository userRepository;
    private final IslandDomainService islandService;

    public IslandService(
            IslandRepository islandRepository,
            UserRepository userRepository,
            IslandDomainService islandService) {
        this.islandRepository = islandRepository;
        this.userRepository = userRepository;
        this.islandService = islandService;
    }

    public List<Island> getAllIslands() {
        return islandRepository.findAll();
    }

    public List<Island> getIslandWithAvailableWorkstations() {
        return islandRepository.findIslandsWithAvailableWorkstations();
    }

   
    @Transactional
    public void alocarWorkstationDisponivel(@NonNull Integer userId) {
        
        final var user = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException());

        final var availableIslands = islandRepository.findIslandsWithAvailableWorkstations();

        final var island = islandService.findAlmostFilledIsland(availableIslands);
        islandService.allocateUserToIsland(island, user);

        islandRepository.save(island);
    }
}
