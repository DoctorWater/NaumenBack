package ru.malkov.naumentesttask.Entities;

import lombok.*;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Person {
    private String name;
    private Integer age;
}
