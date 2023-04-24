package ru.malkov.naumentesttask.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.malkov.naumentesttask.Services.StatisticsService;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;

    public StatisticsController(@Autowired StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping(value = "/frequency")
    public HashMap<String, Integer> getFrequencyMap(){
        return statisticsService.getFrequencyHashmap();
    }

    @GetMapping(value = "/highest-age")
    public String getOldest(){
        Comparator<Integer> comparator = (integer, t1) -> {
            if (integer < t1)
                return -1;
            if (integer.equals(t1))
                return 0;
            return 1;
        };
        Optional<Integer> highestAge = statisticsService.getFrequencyHashmap().values().stream().max(comparator);
        if(highestAge.isPresent()){
            for(Map.Entry<String, Integer> entry: statisticsService.getFrequencyHashmap().entrySet()){
                if(entry.getValue().equals(highestAge.get())){
                    return entry.getKey();
                }
            }
        }
        return "No names registered";
    }
}
