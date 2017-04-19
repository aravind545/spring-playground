package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by trainer9 on 4/18/17.
 */
@RestController
@RequestMapping("/math")
public class PiController {

    @GetMapping("/pi")
    public String getPi()
    {
        return "3.141592653589793";
    }
}
