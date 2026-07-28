package com.example.assignment1roomdatabase.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.assignment1roomdatabase.entity.Course;
import com.example.assignment1roomdatabase.entity.Enrollment;
import com.example.assignment1roomdatabase.entity.Student;
import com.example.assignment1roomdatabase.repository.AppRepository;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppViewModel extends AndroidViewModel {

    private final AppRepository repository;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    private final MutableLiveData<List<Student>> students = new MutableLiveData<>();
    private final MutableLiveData<List<Course>> courses = new MutableLiveData<>();
    private final MutableLiveData<List<Enrollment>> enrollments = new MutableLiveData<>();
    private final MutableLiveData<String> message = new MutableLiveData<>();

    public AppViewModel(@NonNull Application application) {
        super(application);
        repository = new AppRepository(application);
    }

    public LiveData<List<Student>> getStudents() {
        return students;
    }

    public LiveData<List<Course>> getCourses() {
        return courses;
    }

    public LiveData<List<Enrollment>> getEnrollments() {
        return enrollments;
    }

    public LiveData<String> getMessage() {
        return message;
    }

    public void loadStudents() {
        executor.execute(() -> students.postValue(repository.getAllStudents()));
    }

    public void loadCourses() {
        executor.execute(() -> courses.postValue(repository.getAllCourses()));
    }

    public void loadEnrollments() {
        executor.execute(() -> enrollments.postValue(repository.getAllEnrollments()));
    }

    public void loadAll() {
        loadStudents();
        loadCourses();
        loadEnrollments();
    }

    public void addStudent(String name, String email, int age, String major) {
        executor.execute(() -> {
            long result = repository.insertStudent(new Student(name, email, age, major));
            message.postValue(result != -1 ? "Student added successfully" : "Failed to add student");
            loadStudents();
        });
    }

    public void deleteStudent(int studentId) {
        executor.execute(() -> {
            int result = repository.deleteStudent(studentId);
            message.postValue(result > 0 ? "Student deleted successfully" : "Failed to delete student");
            loadStudents();
            loadEnrollments();
        });
    }

    public void addCourse(String courseCode, String title, int creditHours) {
        executor.execute(() -> {
            long result = repository.insertCourse(new Course(courseCode, title, creditHours));
            message.postValue(result != -1 ? "Course added successfully" : "Failed to add course");
            loadCourses();
        });
    }

    public void deleteCourse(int courseId) {
        executor.execute(() -> {
            int result = repository.deleteCourse(courseId);
            message.postValue(result > 0 ? "Course deleted successfully" : "Failed to delete course");
            loadCourses();
            loadEnrollments();
        });
    }

    public void addEnrollment(int studentId, int courseId, String semester, String grade) {
        executor.execute(() -> {
            long result = repository.insertEnrollment(
                    new Enrollment(studentId, courseId, semester, grade));
            message.postValue(result != -1 ? "Enrollment added successfully" : "Failed to add enrollment");
            loadEnrollments();
        });
    }

    public void deleteEnrollment(int enrollmentId) {
        executor.execute(() -> {
            int result = repository.deleteEnrollment(enrollmentId);
            message.postValue(result > 0 ? "Enrollment deleted successfully" : "Failed to delete enrollment");
            loadEnrollments();
        });
    }

    public void clearMessage() {
        message.setValue(null);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        executor.shutdownNow();
    }
}
