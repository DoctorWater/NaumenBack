package ru.malkov.naumentesttask.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.malkov.naumentesttask.Exceptions.NoSuchNameWasFoundException;
import ru.malkov.naumentesttask.Interfaces.DAO;
import ru.malkov.naumentesttask.Services.StatisticsService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "people")
public class PeopleController {
    private final DAO dataAccess;
    private final StatisticsService statisticsService;

    @GetMapping(value = "/getAgeByName/{name}")
    public Integer getAgeByName(@PathVariable String name) {
        Integer DEFAULT_AGE = 999;
        try {
            statisticsService.addNewRequest(name);
            return dataAccess.getPersonByName(name).age();
        } catch (NoSuchNameWasFoundException e) {
            return DEFAULT_AGE;
        }
    }
}
