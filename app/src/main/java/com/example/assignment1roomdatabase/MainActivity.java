package com.example.assignment1roomdatabase;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.assignment1roomdatabase.entity.Course;
import com.example.assignment1roomdatabase.entity.Enrollment;
import com.example.assignment1roomdatabase.entity.Student;
import com.example.assignment1roomdatabase.viewmodel.AppViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etName, etEmail, etAge, etMajor;
    EditText etCourseCode, etTitle, etCreditHours;
    EditText etStudentId, etCourseId, etSemester, etGrade;
    Button btnAddStudent, btnAddCourse, btnAddEnrollment, btnShow;
    TextView tvResult;
    AppViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(AppViewModel.class);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etAge = findViewById(R.id.etAge);
        etMajor = findViewById(R.id.etMajor);
        etCourseCode = findViewById(R.id.etCourseCode);
        etTitle = findViewById(R.id.etTitle);
        etCreditHours = findViewById(R.id.etCreditHours);
        etStudentId = findViewById(R.id.etStudentId);
        etCourseId = findViewById(R.id.etCourseId);
        etSemester = findViewById(R.id.etSemester);
        etGrade = findViewById(R.id.etGrade);
        btnAddStudent = findViewById(R.id.btnAddStudent);
        btnAddCourse = findViewById(R.id.btnAddCourse);
        btnAddEnrollment = findViewById(R.id.btnAddEnrollment);
        btnShow = findViewById(R.id.btnShow);
        tvResult = findViewById(R.id.tvResult);

        btnAddStudent.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String ageText = etAge.getText().toString().trim();
            String major = etMajor.getText().toString().trim();

            if (name.equals("") || email.equals("") || ageText.equals("") || major.equals("")) {
                Toast.makeText(this, "Enter all student fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int age = Integer.parseInt(ageText);
            viewModel.insertStudent(name, email, age, major);
            Toast.makeText(this, "Student added", Toast.LENGTH_SHORT).show();
            etName.setText("");
            etEmail.setText("");
            etAge.setText("");
            etMajor.setText("");
        });

        btnAddCourse.setOnClickListener(v -> {
            String courseCode = etCourseCode.getText().toString().trim();
            String title = etTitle.getText().toString().trim();
            String hoursText = etCreditHours.getText().toString().trim();

            if (courseCode.equals("") || title.equals("") || hoursText.equals("")) {
                Toast.makeText(this, "Enter all course fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int creditHours = Integer.parseInt(hoursText);
            viewModel.insertCourse(courseCode, title, creditHours);
            Toast.makeText(this, "Course added", Toast.LENGTH_SHORT).show();
            etCourseCode.setText("");
            etTitle.setText("");
            etCreditHours.setText("");
        });

        btnAddEnrollment.setOnClickListener(v -> {
            String studentIdText = etStudentId.getText().toString().trim();
            String courseIdText = etCourseId.getText().toString().trim();
            String semester = etSemester.getText().toString().trim();
            String grade = etGrade.getText().toString().trim();

            if (studentIdText.equals("") || courseIdText.equals("") || semester.equals("") || grade.equals("")) {
                Toast.makeText(this, "Enter all enrollment fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int studentId = Integer.parseInt(studentIdText);
            int courseId = Integer.parseInt(courseIdText);

            try {
                viewModel.insertEnrollment(studentId, courseId, semester, grade);
                Toast.makeText(this, "Enrollment added", Toast.LENGTH_SHORT).show();
                etStudentId.setText("");
                etCourseId.setText("");
                etSemester.setText("");
                etGrade.setText("");
            } catch (Exception e) {
                Toast.makeText(this, "Student ID or Course ID not found", Toast.LENGTH_SHORT).show();
            }
        });

        btnShow.setOnClickListener(v -> {
            String text = "Students:\n";
            List<Student> students = viewModel.getAllStudents();
            for (int i = 0; i < students.size(); i++) {
                Student s = students.get(i);
                text = text + s.getStudentId() + " - " + s.getName() + " - " + s.getEmail()
                        + " - " + s.getAge() + " - " + s.getMajor() + "\n";
            }

            text = text + "\nCourses:\n";
            List<Course> courses = viewModel.getAllCourses();
            for (int i = 0; i < courses.size(); i++) {
                Course c = courses.get(i);
                text = text + c.getCourseId() + " - " + c.getCourseCode() + " - " + c.getTitle()
                        + " - " + c.getCreditHours() + " hrs\n";
            }

            text = text + "\nEnrollments:\n";
            List<Enrollment> enrollments = viewModel.getAllEnrollments();
            for (int i = 0; i < enrollments.size(); i++) {
                Enrollment e = enrollments.get(i);
                text = text + e.getEnrollmentId() + " - studentId: " + e.getStudentId()
                        + " - courseId: " + e.getCourseId()
                        + " - " + e.getSemester() + " - " + e.getGrade() + "\n";
            }

            tvResult.setText(text);
        });
    }
}
