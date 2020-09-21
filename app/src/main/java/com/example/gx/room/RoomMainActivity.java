package com.example.gx.room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.gx.R;

import java.util.List;

/**
 * Activity应该是管理生命周期和响应用户的界面，和数据相关的不应放到activity内，应该放到ViewModel中
 */
public class RoomMainActivity extends AppCompatActivity {

    TextView textView;
    Button buttonInsert, buttonUpdate, buttonDelete, buttonClear;

    WordViewModel wordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_main);

        wordViewModel = ViewModelProviders.of(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(WordViewModel.class);
        textView = findViewById(R.id.textView14);
        wordViewModel.getAllWordsLive().observe(this, new Observer<List<Word>>() {
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
                wordViewModel.insertWords(word, word2);
            }
        });

        buttonClear = findViewById(R.id.clear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wordViewModel.deleteAllWords();
            }
        });

        buttonDelete = findViewById(R.id.delete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word word = new Word("", "");
                word.setId(2);
                wordViewModel.deleteWords(word);
            }
        });

        buttonUpdate = findViewById(R.id.update);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word word = new Word("hi", "你好啊");
                word.setId(5);
                wordViewModel.updateWords(word);
            }
        });
    }
}