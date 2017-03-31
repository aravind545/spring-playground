package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by trainer9 on 3/30/17.
 */
@RestController
public class HelloController {

    @GetMapping("/")
    public String helloWorld() {
        return "Hello from Spring!";
    }

}