package ru.malkov.naumentesttask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.malkov.naumentesttask.Exceptions.DaoCreationException;
import ru.malkov.naumentesttask.Interfaces.DAO;
import ru.malkov.naumentesttask.Services.FileDao;

@SpringBootApplication
public class NaumenTestTaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(NaumenTestTaskApplication.class, args);
    }

    @Bean
    public DAO initiatePeopleCollectionFromFile() throws DaoCreationException {
        final String FILENAME = "src/main/resources/static/data.txt";
        return new FileDao(FILENAME);
    }
}


