package com.example.gx.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gx.R;

import java.util.List;

public class RoomMainActivity extends AppCompatActivity {

    WordDatabase wordDatabase;
    WordDao wordDao;

    TextView textView;
    Button buttonInsert, buttonUpdate, buttonDelete, buttonClear;

    LiveData<List<Word>> allWordsLive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_main);

        wordDatabase = Room.databaseBuilder(this, WordDatabase.class, "word_database")
                .allowMainThreadQueries() // 测试时可以指定在主线程执行CRUD操作，实际使用时具体的CRUD须在线程中执行
                .build();
        wordDao = wordDatabase.getWordDao();
        allWordsLive = wordDao.getAllWordsLive();
        textView = findViewById(R.id.textView14);
        allWordsLive.observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                StringBuilder text = new StringBuilder();
                for (int i = 0; i < words.size(); i++) {
                    Word word = words.get(i);
                    text.append(word.getId()).append(":").append(word.getWord()).append("=").append(word.getChineseMeaning()).append("\n");
                }
                textView.setText(text.toString());
            }
        });

        buttonInsert = findViewById(R.id.insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word word = new Word("hello", "你好");
                Word word2 = new Word("World!", "世界！");
                wordDao.insertWords(word, word2);
            }
        });

        buttonClear = findViewById(R.id.clear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wordDao.deleteAllWords();
            }
        });

        buttonDelete = findViewById(R.id.delete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word word = new Word("", "");
                word.setId(2);
                wordDao.deleteWords(word);
            }
        });

        buttonUpdate = findViewById(R.id.update);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word word = new Word("hi", "你好啊");
                word.setId(5);
                wordDao.updateWords(word);
            }
        });
    }
}