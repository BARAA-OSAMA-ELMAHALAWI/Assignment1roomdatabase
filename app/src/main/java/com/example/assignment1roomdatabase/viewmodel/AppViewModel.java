package com.example.assignment1roomdatabase.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.assignment1roomdatabase.entity.Course;
import com.example.assignment1roomdatabase.entity.Enrollment;
import com.example.assignment1roomdatabase.entity.Student;
import com.example.assignment1roomdatabase.repository.AppRepository;

import java.util.List;

public class AppViewModel extends AndroidViewModel {

    private AppRepository repository;

    public AppViewModel(@NonNull Application application) {
        super(application);
        repository = new AppRepository(application);
    }

    public void insertStudent(String name, String email, int age, String major) {
        Student student = new Student(name, email, age, major);
        repository.insertStudent(student);
    }

    public List<Student> getAllStudents() {
        return repository.getAllStudents();
    }

    public void insertCourse(String courseCode, String title, int creditHours) {
        Course course = new Course(courseCode, title, creditHours);
        repository.insertCourse(course);
    }

    public List<Course> getAllCourses() {
        return repository.getAllCourses();
    }

    public void insertEnrollment(int studentId, int courseId, String semester, String grade) {
        Enrollment enrollment = new Enrollment(studentId, courseId, semester, grade);
        repository.insertEnrollment(enrollment);
    }

    public List<Enrollment> getAllEnrollments() {
        return repository.getAllEnrollments();
    }
}
