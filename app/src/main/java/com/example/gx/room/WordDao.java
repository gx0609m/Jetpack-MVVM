package com.example.gx.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * 若有多个Entity，应该写多个Dao
 *
 * created by gaoxiang on 2020/9/21
 */
@Dao // Database access object
public interface WordDao {

    @Insert
    void insertWords(Word... words);

    @Update
    void updateWords(Word... words);

    @Delete
    void deleteWords(Word... words);

    @Query("DELETE FROM WORD") // 删除
    void deleteAllWords();

    @Query("SELECT * FROM WORD ORDER BY ID DESC") // 查询
//    List<Word> getAllWords();
    LiveData<List<Word>> getAllWordsLive();
}
