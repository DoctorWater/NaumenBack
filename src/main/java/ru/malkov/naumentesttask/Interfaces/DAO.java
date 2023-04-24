package ru.malkov.naumentesttask.Interfaces;

import ru.malkov.naumentesttask.Entities.Person;
import ru.malkov.naumentesttask.Exceptions.DaoCreationException;
import ru.malkov.naumentesttask.Exceptions.NoSuchNameWasFoundException;

import java.io.FileNotFoundException;
import java.util.List;

public interface DAO {
    Person getPersonByName(String name) throws NoSuchNameWasFoundException;
}
