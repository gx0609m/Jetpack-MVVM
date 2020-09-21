package com.example.gx.paging;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * created by gaoxiang on 2020/9/21
 */
@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class StudentsDatabase extends RoomDatabase {

    static StudentsDatabase INSTANCE;

    static synchronized StudentsDatabase getInstance(Context context) {
        if (null == INSTANCE) {
            INSTANCE = Room.databaseBuilder(context, StudentsDatabase.class, "student_database")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    abstract StudentDao getStudentDao();
}
