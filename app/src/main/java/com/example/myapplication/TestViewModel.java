package com.example.myapplication;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TestViewModel extends ViewModel {
    private MutableLiveData<String> mAddressEvent;

    public MutableLiveData<String> getAddressEvent() {
        if (mAddressEvent == null) {
            mAddressEvent = new MutableLiveData<>();
            mAddressEvent.postValue("深圳");
        }
        return mAddressEvent;
    }

    public void updateAddress(String address) {
        mAddressEvent.postValue(address);
    }

}
