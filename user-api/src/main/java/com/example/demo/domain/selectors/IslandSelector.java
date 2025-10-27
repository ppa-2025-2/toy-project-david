package com.example.demo.domain.selectors;

import java.util.List;
import java.util.Comparator;

import org.springframework.stereotype.Component;

import com.example.demo.repository.entity.Island;

@Component
public class IslandSelector {

    public Island selectIslandToFill(List<Island> availableIslands) {
        if (availableIslands == null || availableIslands.isEmpty()) {
            throw new IllegalStateException("Workstations not available");
        }

        return availableIslands.stream()
                .min(Comparator.comparingLong(Island::countOccupiedWorkstations))
                .get();
    }
}
