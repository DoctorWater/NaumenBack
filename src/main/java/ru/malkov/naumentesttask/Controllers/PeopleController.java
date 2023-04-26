package ru.malkov.naumentesttask.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.malkov.naumentesttask.DTO.AgeDto;
import ru.malkov.naumentesttask.DTO.ApiDto;
import ru.malkov.naumentesttask.Entities.Person;
import ru.malkov.naumentesttask.Exceptions.NoSuchNameWasFoundException;
import ru.malkov.naumentesttask.Interfaces.DAO;
import ru.malkov.naumentesttask.Services.StatisticsService;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "people")
public class PeopleController {
    private final Integer DEFAULT_AGE = 999;
    private final DAO dataAccess;
    private final StatisticsService statisticsService;

    @GetMapping(value = "/get-age-by-name/{name}")
    public AgeDto getAgeByName(@PathVariable String name) {
        try {
            statisticsService.addNewRequest(name);
            return new AgeDto(dataAccess.getPersonByName(name).getAge());
        } catch (NoSuchNameWasFoundException e) {
            return new AgeDto(DEFAULT_AGE);
        }
    }

    @GetMapping(value = "/highest-age")
    public String getOldest() {
        Comparator<Person> ageComparator = (o1, o2) -> {
            if (o1.getAge() < o2.getAge()) {
                return -1;
            } else {
                if (o1.getAge().equals(o2.getAge())) {
                    return 0;
                }
            }
            return 1;
        };
        Optional<Person> oldestPerson = dataAccess.getAllPeople().stream().max(ageComparator);
        if (oldestPerson.isPresent()) {
            return oldestPerson.get().getName();
        }
        return "No names registered";
    }

    @GetMapping(value = "api/get-person-by-name/{name}")
    public ApiDto getPersonApi(@PathVariable String name) {
        try {
            Person foundPerson = dataAccess.getPersonByName(name);
            Integer requestNumber = statisticsService.getFrequencyHashmap().get(foundPerson.getName());
            if (requestNumber == null) {
                requestNumber = 0;
            }
            return new ApiDto(foundPerson.getAge(), requestNumber, foundPerson.getName());
        } catch (NoSuchNameWasFoundException e) {
            return new ApiDto(DEFAULT_AGE, 0, "No person found");
        }

    }
}
