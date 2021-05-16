package com.example.imustjdchain.ui.query;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class QueryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public QueryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("查询页面");
    }

    public LiveData<String> getText() {
        return mText;
    }
}