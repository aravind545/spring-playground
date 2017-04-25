package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by trainer9 on 4/25/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(MathVolumeController.class)
public class MathVolumeControllerTest {

    @Autowired
    private
    MockMvc mvc;



    @Test
    public void testGetVolume() throws Exception {
        int length = 4;
        int width = 3;
        int height = 2;

        this.mvc.perform(post(String.format("/math/volume/%d/%d/%d", length,width,height)))
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 4x3x2 rectangle is 24"));
    }

}


