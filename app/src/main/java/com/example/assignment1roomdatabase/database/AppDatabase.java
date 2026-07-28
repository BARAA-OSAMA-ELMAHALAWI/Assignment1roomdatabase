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
        version = 1,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public abstract StudentDao studentDao();

    public abstract CourseDao courseDao();

    public abstract EnrollmentDao enrollmentDao();

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "university.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
