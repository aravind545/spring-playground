package com.example;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by trainer9 on 4/25/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PatchController.class)
public class PatchControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    public void testPatchEndpoint() throws Exception{
        mvc.perform(patch("/movies/48/update"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json("{\n" +
                        "  \"id\": 48,\n" +
                        "  \"title\": \"Title\"\n" +
                        "}"));
    }

}
