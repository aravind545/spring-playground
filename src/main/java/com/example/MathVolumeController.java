package com.example;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by trainer9 on 4/25/17.
 */
@RestController
public class MathVolumeController {


    @RequestMapping("/math/volume/{length}/{width}/{height}")
    public String getVolume(@PathVariable int length, @PathVariable int width, @PathVariable int height) {
        return String.format("The volume of a %dx%dx%d rectangle is %d", length, width, height, length * width * height);
    }


}

