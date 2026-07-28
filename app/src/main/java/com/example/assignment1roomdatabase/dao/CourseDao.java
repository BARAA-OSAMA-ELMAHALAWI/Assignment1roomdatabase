package com.example.assignment1roomdatabase.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.assignment1roomdatabase.entity.Course;

import java.util.List;

@Dao
public interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Course course);

    @Update
    int update(Course course);

    @Delete
    int delete(Course course);

    @Query("DELETE FROM courses WHERE courseId = :courseId")
    int deleteById(int courseId);

    @Query("SELECT * FROM courses ORDER BY courseId DESC")
    List<Course> getAllCourses();

    @Query("SELECT * FROM courses WHERE courseId = :courseId")
    Course getCourseById(int courseId);
}
