package com.example.imustjdchain.ui.upload;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UploadViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public UploadViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("上传页面");
    }

    public LiveData<String> getText() {
        return mText;
    }
}