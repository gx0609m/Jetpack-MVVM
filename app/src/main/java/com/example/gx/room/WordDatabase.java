package com.example.gx.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * created by gaoxiang on 2020/9/21
 */
@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {

    public abstract WordDao getWordDao();
}
