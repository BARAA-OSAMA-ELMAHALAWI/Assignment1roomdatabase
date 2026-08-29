package com.example.assignment1roomdatabase.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.assignment1roomdatabase.entity.Enrollment;

import java.util.List;

@Dao
public interface EnrollmentDao {

    @Insert
    void insert(Enrollment enrollment);

    @Query("SELECT * FROM enrollments")
    List<Enrollment> getAllEnrollments();
}
