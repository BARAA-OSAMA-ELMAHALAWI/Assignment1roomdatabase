package com.example.assignment1roomdatabase.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.assignment1roomdatabase.entity.Course;

import java.util.List;

@Dao
public interface CourseDao {

    @Insert
    void insert(Course course);

    @Query("SELECT * FROM courses")
    List<Course> getAllCourses();
}
