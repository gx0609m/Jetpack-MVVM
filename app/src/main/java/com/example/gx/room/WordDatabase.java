package com.example.gx.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * created by gaoxiang on 2020/9/21
 */
// use singleton
@Database(entities = {Word.class}, version = 2, exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {

    private static WordDatabase INSTANCE;

    static WordDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WordDatabase.class, "word_database")
                    .allowMainThreadQueries() // 测试时可以指定在主线程执行CRUD操作，实际使用时具体的CRUD须在线程中执行
                    .addMigrations(MIGRATION_1_2)
                    .build();
        }
        return INSTANCE;
    }

    public abstract WordDao getWordDao();

    // 表示数据库从版本 1 --> 2
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // 哪个表中 增加了哪个字段
            database.execSQL("ALTER TABLE word ADD COLUMN bar_data INTEGER NOT NULL DEFAULT 1");
        }
    };

    // 删除某个字段的数据库迁移比较麻烦，因为sqlite中只有"add column"，没有"delete column"这样的操作
    static final Migration MIGRATION_2_3 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // 需要现在Entity中删除相应的字段和字段的 getter and setter
            // 创建删除了部分字段后的表作为临时表
            database.execSQL("CREATE TABLE word_temp (id INTEGER PRIMARY KEY NOT NULL ,english_word TEXT," +
                    "chinese_meaning TEXT)");
            // 从旧表中查找出想要的字段放到临时表中
            database.execSQL("INSERT INTO word_temp (id, english_word, chinese_meaning)" +
                    "SELECT id,english_word,chinese_meaning FROM word");
            // 删除旧表
            database.execSQL("DROP TABLE word");
            // 将临时表命名为旧表
            database.execSQL("ALTER TABLE word_temp RENAME to word");
        }
    };
}
