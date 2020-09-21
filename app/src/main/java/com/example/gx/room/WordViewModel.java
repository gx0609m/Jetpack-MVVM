package com.example.gx.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * created by gaoxiang on 2020/9/21
 */
public class WordViewModel extends AndroidViewModel {

    private WordDao wordDao;
    private LiveData<List<Word>> allWordsLive;

    public WordViewModel(@NonNull Application application) {
        super(application);
        WordDatabase wordDatabase = WordDatabase.getInstance(application);
        wordDao = wordDatabase.getWordDao();
        allWordsLive = wordDao.getAllWordsLive(); // 返回liveData的操作系统会自动放到主线程中，不需要再在线程中去执行了
    }

    public LiveData<List<Word>> getAllWordsLive(){
        return allWordsLive;
    }

    public void insertWords(Word... words) {
        wordDao.insertWords(words); // 数据库插入需要放到线程中，为方便测试创建database时指定了allowMainThreadQueries，则可以放到主线程中执行
    }

    public void updateWords(Word... words) {
        wordDao.updateWords(words); // 需要放到线程中，为方便测试创建database时指定了allowMainThreadQueries，则可以放到主线程中执行
    }

    public void deleteWords(Word... words) {
        wordDao.deleteWords(words); // 需要放到线程中，为方便测试创建database时指定了allowMainThreadQueries，则可以放到主线程中执行
    }

    public void deleteAllWords(Word... words) {
        wordDao.deleteWords(words); // 需要放到线程中，为方便测试创建database时指定了allowMainThreadQueries，则可以放到主线程中执行
    }
}
