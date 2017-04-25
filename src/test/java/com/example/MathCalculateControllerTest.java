package com.example;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
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
@WebMvcTest(MathCalculateController.class)
public class MathCalculateControllerTest {


    @Autowired
    MockMvc mvc;

    @Test
    public void testMathCalculateAdd() throws Exception {
        this.mvc.perform(get("/math/calculate?operation=add&x=10&y=10").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("10 + 10 = 20"));
    }


    @Test
    public void testMathCalculateSubtract() throws Exception {
        this.mvc.perform(get("/math/calculate?operation=subtract&x=50&y=25").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("50 - 25 = 25"));
    }

    @Test
    public void testMathCalculateMultiply() throws Exception {
        this.mvc.perform(get("/math/calculate?operation=multiply&x=5&y=5").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("5 * 5 = 25"));
    }

    @Test
    public void testMathCalculateDivide() throws Exception {
        this.mvc.perform(get("/math/calculate?operation=divide&x=50&y=25").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("50 / 25 = 2"));
    }

    @Test
    public void testsum() throws Exception {
        this.mvc.perform(post("/math/sum?n=4&n=5&n=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 5 + 6 = 15"));
    }
}

