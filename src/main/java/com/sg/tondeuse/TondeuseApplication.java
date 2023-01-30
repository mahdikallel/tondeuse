package com.sg.tondeuse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TondeuseApplication {

    @Autowired
    private Runner runner;

    public static void main(String[] args) {
        SpringApplication.run(TondeuseApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunnerBean() {
        return (args) -> runner.run(args);
    }
}

