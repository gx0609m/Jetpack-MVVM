package com.example.gx.room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gx.R;

import java.util.List;

/**
 * Activity应该是管理生命周期和响应用户的界面，和数据相关的不应放到activity内，应该放到ViewModel中
 */
public class RoomMainActivity extends AppCompatActivity {

    Button buttonInsert, buttonUpdate, buttonDelete, buttonClear;
    WordViewModel wordViewModel;
    RecyclerView recyclerView;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_main);
        recyclerView = findViewById(R.id.recyclerView);
        myAdapter = new MyAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
        wordViewModel = ViewModelProviders.of(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(WordViewModel.class);
        wordViewModel.getAllWordsLive().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                myAdapter.setAllWords(words);
                myAdapter.notifyDataSetChanged();
            }
        });

        buttonInsert = findViewById(R.id.insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] english = {"hello", "world", "Android", "Google", "studio", "project", "database", "recycler", "view", "String", "value", "Integer"};
                String[] chinese = {"你好", "世界", "安卓", "谷歌", "工作室", "工程", "数据库", "回收站", "视图", "字符串", "价值", "整形值"};
                for (int i = 0; i < english.length; i++) {
                    wordViewModel.insertWords(new Word(english[i], chinese[i]));
                }
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