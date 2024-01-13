package com.luv2code.springboot.demo.mycoolapp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FunRestController {

    /**
     * Expose "/" that return "Hello World"
     */
    @GetMapping("/")
    public String sayHello() {
        return "Hello World!";
    }

    /**
     * Expose a new endpoint for "workout"
     */
    @GetMapping("/workout")
    public String getDailyWorkout() {
        return "Run a hard 5k!";
    }

    /**
     * Expose a new endpoint for "workout"
     */
    @GetMapping("/fortune")
    public String getDailyFortune() {
        return "Today is your lucky day.";
    }
}
