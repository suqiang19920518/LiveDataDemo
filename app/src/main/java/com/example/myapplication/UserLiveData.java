package com.example.myapplication;

import androidx.lifecycle.LiveData;

public class UserLiveData extends LiveData<User> {

    private static UserLiveData mUserLiveData;

    public UserLiveData() {

    }

    public static UserLiveData getInstance() {
        if (mUserLiveData == null) {
            mUserLiveData = new UserLiveData();
        }
        return mUserLiveData;
    }

    @Override
    public void postValue(User value) {
        super.postValue(value);
    }

    @Override
    public void setValue(User value) {
        super.setValue(value);
    }
}
