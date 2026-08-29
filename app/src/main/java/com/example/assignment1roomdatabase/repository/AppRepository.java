package com.example.assignment1roomdatabase.repository;

import android.content.Context;

import com.example.assignment1roomdatabase.dao.CourseDao;
import com.example.assignment1roomdatabase.dao.EnrollmentDao;
import com.example.assignment1roomdatabase.dao.StudentDao;
import com.example.assignment1roomdatabase.database.AppDatabase;
import com.example.assignment1roomdatabase.entity.Course;
import com.example.assignment1roomdatabase.entity.Enrollment;
import com.example.assignment1roomdatabase.entity.Student;

import java.util.List;

public class AppRepository {

    private StudentDao studentDao;
    private CourseDao courseDao;
    private EnrollmentDao enrollmentDao;

    public AppRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        studentDao = db.studentDao();
        courseDao = db.courseDao();
        enrollmentDao = db.enrollmentDao();
    }

    public void insertStudent(Student student) {
        studentDao.insert(student);
    }

    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    public void insertCourse(Course course) {
        courseDao.insert(course);
    }

    public List<Course> getAllCourses() {
        return courseDao.getAllCourses();
    }

    public void insertEnrollment(Enrollment enrollment) {
        enrollmentDao.insert(enrollment);
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentDao.getAllEnrollments();
    }
}
