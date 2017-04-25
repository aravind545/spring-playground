package com.example;

import com.example.model.Shape;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by trainer9 on 4/25/17.
 */
@RestController
public class MathAreaController {
    @PostMapping("/math/area")
    public String mathArea(Shape dimensions){

        Integer width = dimensions.getWidth();
        Integer height = dimensions.getHeight();

        if(dimensions.getType()
                == null || (!dimensions.getType().equalsIgnoreCase("circle") && !dimensions.getType().equalsIgnoreCase("rectangle"))){
            return "Invalid";
        }
        if(dimensions.getType().equalsIgnoreCase("circle")){
            if(dimensions.getRadius() == null){
                return "Invalid";
            }else{
                return String.format("Area of a circle with a radius of %s is %s", dimensions.getRadius(), 3.14 * (dimensions.getRadius() * dimensions.getRadius()));
            }
        }
        if(dimensions.getType().equalsIgnoreCase("rectangle")){
            if(dimensions.getWidth() == null || dimensions.getHeight() == null){
                return "Invalid";
            }else{
                return String.format("Area of a %sx%s rectangle is %s", width, height, width*height);
            }

        }
        return null;
    }
}
