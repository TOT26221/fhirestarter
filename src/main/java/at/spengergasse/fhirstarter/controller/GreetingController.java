package at.spengergasse.fhirstarter.controller;


import at.spengergasse.fhirstarter.model.Greeting;
import at.spengergasse.fhirstarter.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

// for http Request handling
@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    GreetingRepository greetingRepository;

    @GetMapping("/greeting")
    public Iterable<Greeting> greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        //return new Greeting(counter.incrementAndGet(), String.format(template, name));
        return greetingRepository.findAll();
    }
}
