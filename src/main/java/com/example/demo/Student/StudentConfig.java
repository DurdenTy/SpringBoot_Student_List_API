package com.example.demo.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student maria =
                    new Student(1L,
                            "Maria",
                            "maria@students.com",
                            LocalDate.of(1999, Month.APRIL, 17));

            Student Clarice = new Student(2L,
                    "Clarice",
                    "clarice@students.com",
                    LocalDate.of(2001, Month.AUGUST, 6));

            repository.saveAll(
                    List.of(maria, Clarice)
            );

        };

    }

}
