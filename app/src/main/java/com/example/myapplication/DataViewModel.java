package com.example.myapplication;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DataViewModel extends ViewModel {
    private UserLiveData user;
    private MutableLiveData<String> name;
    private MutableLiveData<Integer> age;
    private MutableLiveData<String> userInfo;

    public MutableLiveData<String> getName() {
        if (name == null) {
            name = new MutableLiveData<>();
            //获取初始值的操作，否则默认为""
            name.setValue("JohnLiu");//看需求，可以换成进行网络请求来下载数据
        }
        return name;
    }

    public MutableLiveData<Integer> getAge() {
        if (age == null) {
            age = new MutableLiveData<>();
            //获取初始值的操作，否则默认为0
            age.setValue(22);//看需求，可以换成进行网络请求来下载数据
        }
        return age;
    }

    public MutableLiveData<String> getUserInfo() {
        if (userInfo == null) {
            userInfo = new MutableLiveData<>();
        }
        return userInfo;
    }

    public UserLiveData getUser() {
        if (user == null) {
            user = new UserLiveData();
        }
        return user;
    }
}
