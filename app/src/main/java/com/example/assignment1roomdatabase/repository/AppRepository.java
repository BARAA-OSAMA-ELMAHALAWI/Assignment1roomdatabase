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

    private final StudentDao studentDao;
    private final CourseDao courseDao;
    private final EnrollmentDao enrollmentDao;

    public AppRepository(Context context) {
        AppDatabase database = AppDatabase.getDatabase(context);
        studentDao = database.studentDao();
        courseDao = database.courseDao();
        enrollmentDao = database.enrollmentDao();
    }

    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    public Student getStudentById(int studentId) {
        return studentDao.getStudentById(studentId);
    }

    public long insertStudent(Student student) {
        return studentDao.insert(student);
    }

    public int updateStudent(Student student) {
        return studentDao.update(student);
    }

    public int deleteStudent(int studentId) {
        return studentDao.deleteById(studentId);
    }

    public List<Course> getAllCourses() {
        return courseDao.getAllCourses();
    }

    public Course getCourseById(int courseId) {
        return courseDao.getCourseById(courseId);
    }

    public long insertCourse(Course course) {
        return courseDao.insert(course);
    }

    public int updateCourse(Course course) {
        return courseDao.update(course);
    }

    public int deleteCourse(int courseId) {
        return courseDao.deleteById(courseId);
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentDao.getAllEnrollments();
    }

    public List<Enrollment> getEnrollmentsByStudent(int studentId) {
        return enrollmentDao.getEnrollmentsByStudent(studentId);
    }

    public List<Enrollment> getEnrollmentsByCourse(int courseId) {
        return enrollmentDao.getEnrollmentsByCourse(courseId);
    }

    public long insertEnrollment(Enrollment enrollment) {
        return enrollmentDao.insert(enrollment);
    }

    public int updateEnrollment(Enrollment enrollment) {
        return enrollmentDao.update(enrollment);
    }

    public int deleteEnrollment(int enrollmentId) {
        return enrollmentDao.deleteById(enrollmentId);
    }
}
