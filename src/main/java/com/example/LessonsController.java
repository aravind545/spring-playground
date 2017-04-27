package com.example;

import com.example.database.LessonRepository;
import com.example.model.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by trainer9 on 4/26/17.
 */

@RestController
@RequestMapping("/lessons")
public class LessonsController {

    private LessonRepository repository;

    public LessonsController(LessonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<Lesson> all() {
        return this.repository.findAll();
    }

    @PostMapping("")
    public Lesson create(@RequestBody Lesson lesson) {
        return this.repository.save(lesson);
    }


}
