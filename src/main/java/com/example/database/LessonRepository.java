package com.example.database;

import com.example.model.Lesson;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by trainer9 on 4/26/17.
 */

public interface LessonRepository extends CrudRepository<Lesson, Long> {
    Lesson findByTitle(String title);

    @Query("select count(*) from Lesson")
    Integer countTitles();

}









