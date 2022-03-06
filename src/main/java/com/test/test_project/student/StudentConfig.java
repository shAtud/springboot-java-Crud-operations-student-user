package com.test.test_project.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return arg->{
            Student sidali = new Student(
                			"sidali",
                			"sidali@gmail.com",
                			LocalDate.of(2000,Month.JANUARY,5)
                		);
             Student hmidouch = new Student(
                		    "hmidouch",
                			"hmidouch@gmail.com",
                			LocalDate.of(2000,Month.JANUARY,5)
                		);
            repository.saveAll(
                List.of(sidali,hmidouch)
                );
        };
    }
}
