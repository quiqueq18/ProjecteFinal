package com.example.projectefinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DescripcioViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private DescripcioViewModel binding;
    public DescripcioViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        return null;
    }



    public LiveData<String> getText() {
        return mText;
    }
}
