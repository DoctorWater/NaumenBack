package ru.malkov.naumentesttask.Services;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class StatisticsService {
    private final HashMap<String, Integer> frequencyHashmap;

    public StatisticsService() {
        this.frequencyHashmap = new HashMap<>();
    }

    public HashMap<String, Integer> getFrequencyHashmap() {
        return frequencyHashmap;
    }

    public void addNewRequest(String name) {
        if (frequencyHashmap.containsKey(name)) {
            frequencyHashmap.replace(name, frequencyHashmap.get(name) + 1);
        } else {
            frequencyHashmap.put(name, 1);
        }
    }
}
