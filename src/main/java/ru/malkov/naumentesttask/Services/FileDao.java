package ru.malkov.naumentesttask.Services;

import ru.malkov.naumentesttask.Entities.Person;
import ru.malkov.naumentesttask.Exceptions.DaoCreationException;
import ru.malkov.naumentesttask.Exceptions.NoSuchNameWasFoundException;
import ru.malkov.naumentesttask.Interfaces.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class FileDao implements DAO {
    private final String filename;
    private final List<Person> people;

    public FileDao(String filename) throws DaoCreationException {
        this.filename = filename;
        this.people = initiateDataList();
    }

    public List<Person> getAllPeople(){
        return people;
    }

    @Override
    public Person getPersonByName(String nameReq) throws NoSuchNameWasFoundException {
        Optional<Person> result = people.stream().filter(x -> x.getName().equals(nameReq)).findFirst();
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new NoSuchNameWasFoundException();
        }
    }

    private List<Person> initiateDataList() throws DaoCreationException {
        try {
            Scanner scanner = new Scanner(new File(filename));
            List<Person> result = new ArrayList<>();
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String name = line.split("_")[0];
                Integer age = Integer.parseInt(line.split("_")[1]);
                result.add(new Person(name, age));
            }
            return result;
        } catch (FileNotFoundException | NumberFormatException e) {
            throw new DaoCreationException(e);
        }
    }
}
