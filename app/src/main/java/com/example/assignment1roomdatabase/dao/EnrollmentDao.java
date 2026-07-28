package com.example.assignment1roomdatabase.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.assignment1roomdatabase.entity.Enrollment;

import java.util.List;

@Dao
public interface EnrollmentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Enrollment enrollment);

    @Update
    int update(Enrollment enrollment);

    @Delete
    int delete(Enrollment enrollment);

    @Query("DELETE FROM enrollments WHERE enrollmentId = :enrollmentId")
    int deleteById(int enrollmentId);

    @Query("SELECT * FROM enrollments ORDER BY enrollmentId DESC")
    List<Enrollment> getAllEnrollments();

    @Query("SELECT * FROM enrollments WHERE studentId = :studentId")
    List<Enrollment> getEnrollmentsByStudent(int studentId);

    @Query("SELECT * FROM enrollments WHERE courseId = :courseId")
    List<Enrollment> getEnrollmentsByCourse(int courseId);
}
