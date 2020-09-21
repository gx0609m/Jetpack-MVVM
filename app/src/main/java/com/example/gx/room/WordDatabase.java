package com.example.gx.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * created by gaoxiang on 2020/9/21
 */
// use singleton
@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {

    private static WordDatabase INSTANCE;

    static WordDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WordDatabase.class, "word_database")
                    .allowMainThreadQueries() // 测试时可以指定在主线程执行CRUD操作，实际使用时具体的CRUD须在线程中执行
                    .build();
        }
        return INSTANCE;
    }

    public abstract WordDao getWordDao();
}
