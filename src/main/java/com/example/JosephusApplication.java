package com.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/josephus")
public class JosephusApplication {

    private static final Logger logger = LogManager.getLogger(JosephusApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(JosephusApplication.class, args);
    }

    // REST endpoint to calculate the last person standing
    @PostMapping("/lastPerson")
    public String lastPersonStanding(@RequestBody JosephusRequest request) {
        logger.info("Received request with step count N = {} and people list size = {}", request.getStepCount(), request.getPeople().size());

        List<String> people = new LinkedList<>(request.getPeople());
        int N = request.getStepCount();
        
        if (N <= 0 || people.isEmpty()) {
            logger.error("Invalid input: N must be greater than 0 and people list cannot be empty.");
            throw new IllegalArgumentException("Invalid input: N must be greater than 0 and people list cannot be empty.");
        }

        String lastPerson = getLastPersonStanding(people, N);

        logger.info("Last person standing is: {}", lastPerson);
        return lastPerson;
    }

    // Function to simulate the Josephus problem
    private String getLastPersonStanding(List<String> people, int N) {
        int index = 0;

        while (people.size() > 1) {
            index = (index + N - 1) % people.size();
            logger.debug("Removing person: {} at index: {}", people.get(index), index);
            people.remove(index);
        }

        logger.debug("Last remaining person is: {}", people.get(0));
        return people.get(0);
    }
}
