package com.example.service;

/**
 * Created by trainer9 on 4/25/17.
 */
public class MathService {

    public String Calculate(String operation, int x, int y) {


     if(operation.equalsIgnoreCase("add")||operation.isEmpty())

    {
        return String.format("%d + %d = %d", x, y, x + y);
    }

        if(operation.equalsIgnoreCase("multiply"))

    {
        return String.format("%d * %d = %d", x, y, x * y);
    }

        if(operation.equalsIgnoreCase("subtract"))

    {
        return String.format("%d - %d = %d", x, y, x - y);
    }

        if(operation.equalsIgnoreCase("divide")&&y!=0)

    {
        return String.format("%d / %d = %d", x, y, x / y);
    }

        return null;
}



}
