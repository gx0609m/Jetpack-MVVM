package com.example.gx.paging;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

/**
 * created by gaoxiang on 2020/9/21
 */
@Dao
public interface StudentDao {

    @Insert
    void insertStudents(Student... students);

    @Query("DELETE FROM student_table")
        // 删除
    void deleteAllStudents();

    @Query("SELECT * FROM student_table ORDER BY id")
        // 查找
        // LiveData<List<Student>> getAllStudentsLive();
        // 由于引入了Paging，这里返回值不用LiveData，而使用 androidx.paging.DataSource.Factory
    DataSource.Factory<Integer, Student> getAllStudents();
}
