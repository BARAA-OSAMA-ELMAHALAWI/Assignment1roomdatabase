package com.example.assignment1roomdatabase.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.assignment1roomdatabase.dao.CourseDao;
import com.example.assignment1roomdatabase.dao.EnrollmentDao;
import com.example.assignment1roomdatabase.dao.StudentDao;
import com.example.assignment1roomdatabase.entity.Course;
import com.example.assignment1roomdatabase.entity.Enrollment;
import com.example.assignment1roomdatabase.entity.Student;

@Database(
        entities = {Student.class, Course.class, Enrollment.class},
        version = 2,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract StudentDao studentDao();

    public abstract CourseDao courseDao();

    public abstract EnrollmentDao enrollmentDao();

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "university.db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration(true)
                    .build();
        }
        return instance;
    }
}
