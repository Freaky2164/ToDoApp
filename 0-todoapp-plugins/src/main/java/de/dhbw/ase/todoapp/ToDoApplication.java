package de.dhbw.ase.todoapp;


import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "de.dhbw.ase.todoapp.plugins")
@EntityScan("de.dhbw.ase.todoapp.domain.*")
public class ToDoApplication
{
    public static void main(String[] args)
    {
        System.setProperty("user.timezone", "UTC");
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(ToDoApplication.class, args);
    }
}
