package com.example;

import com.example.database.LessonRepository;
import com.example.model.Lesson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by trainer9 on 5/3/17.
 */


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LessonsControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mvc;

    @Autowired
    LessonRepository lessonRepository;

    @Before
    public void setUp() {

        Lesson lesson = new Lesson();
        lesson.setTitle("new title");

        lessonRepository.save(lesson);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    @Transactional
    @Commit
    public void testcreateLesson() throws Exception{

        String payload = getJSON("/book.json");
        this.mvc.perform(
                post("/lessons")
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.title", is("aravind book")));

    }


    @Test
    public void testfindbyID() throws Exception {

        this.mvc.perform(
                get("/lessons/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("new title")));
    }





    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }

}


