package com.example;
import com.example.MathAreaController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by trainer9 on 4/25/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(MathAreaController.class)
public class MathAreaControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    public void testCircleArea() throws Exception{
        mvc.perform(post("/math/area?type=circle&radius=5"))
                .andExpect(status().isOk())
                .andExpect(content().string
                        (String.format("Area of a circle with a radius of 5 is %s", 3.14 * (5*5))));
    }

    @Test
    public void testRectangleArea() throws Exception{
        mvc.perform(post("/math/area?type=rectangle&width=4&height=7"))
                .andExpect(status().isOk())
                .andExpect(content().string("Area of a 4x7 rectangle is 28"));
    }

    @Test
    public void invalidRectangleParams() throws Exception{
        mvc.perform(post("/math/area?type=rectangle&radius=5"))
                .andExpect(status().isOk())
                .andExpect(content().string("Invalid"));
    }

    @Test
    public void invalidCircleParams() throws Exception{
        mvc.perform(post("/math/area?type=circle&width=5&height=5"))
                .andExpect(status().isOk())
                .andExpect(content().string("Invalid"));
    }
}
