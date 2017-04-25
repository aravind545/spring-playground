package com.example;

import com.example.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by trainer9 on 4/20/17.
 */
@RestController
@RequestMapping("/math")
public class MathCalculateController {


    private MathService mathService;

    @RequestMapping("/calculate")
    public String getCalculate(@RequestParam String operation, @RequestParam int x, @RequestParam int y)
    {
        mathService = new MathService();
       return mathService.Calculate(operation,x,y);
    }

    @PostMapping("/sum")
    public String getSum(@RequestParam MultiValueMap<String, String> queryString)
    {

        List<String> params = queryString.get( "n" );


        StringBuilder stringBuilder = new StringBuilder();

        int i = 0;

        if (params != null && params.size() > 0) {

            for (int index = 0; index < params.size(); index++) {

                try {
                    i = i + Integer.parseInt(params.get(index));

                    stringBuilder.append(params.get(index));


                    if (index != params.size() - 1) {
                        stringBuilder.append(" + ");
                    }
                } catch (Exception ex) {
                    return "Integer values expected";
                }
            }


            stringBuilder.append(" = ");
            stringBuilder.append(Integer.toString(i));

            return stringBuilder.toString();
        }

        return "0";

    }
}
