package com.example.gx.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * ViewModel是管理界面的数据，但是数据的获取不应是ViewModel的职责，因此我们创建仓库类 WordRepository
 *
 * created by gaoxiang on 2020/9/21
 */
public class WordViewModel extends AndroidViewModel {

    private WordRepository wordRepository;

    public WordViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);
    }

    public LiveData<List<Word>> getAllWordsLive(){
        return wordRepository.getAllWordsLive();
    }

    public void insertWords(Word... words) {
        wordRepository.insertWords(words);
    }

    public void updateWords(Word... words) {
        wordRepository.updateWords(words);
    }

    public void deleteWords(Word... words) {
        wordRepository.deleteWords(words);
    }

    public void deleteAllWords() {
        wordRepository.deleteAllWords();
    }
}
