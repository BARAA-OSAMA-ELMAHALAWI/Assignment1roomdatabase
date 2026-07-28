package com.example.assignment1roomdatabase.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.assignment1roomdatabase.entity.Student;

import java.util.List;

@Dao
public interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Student student);

    @Update
    int update(Student student);

    @Delete
    int delete(Student student);

    @Query("DELETE FROM students WHERE studentId = :studentId")
    int deleteById(int studentId);

    @Query("SELECT * FROM students ORDER BY studentId DESC")
    List<Student> getAllStudents();

    @Query("SELECT * FROM students WHERE studentId = :studentId")
    Student getStudentById(int studentId);
}
