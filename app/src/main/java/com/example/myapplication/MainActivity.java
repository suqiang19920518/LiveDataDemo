package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;

import android.content.Intent;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    private TextView mTvAddress;
    private TextView mTvName;
    private TextView mTvAge;
    private TextView mTvUserInfo;
    private Button mBtnModify;

    private TestViewModel mTestViewModel;
    private DataViewModel mDataViewModel;
    private Observer<NetworkInfo> mNetworkInfoObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        mTestViewModel = ViewModelUtils.getPrivateViewModel(this, TestViewModel.class, this);
        //全局共享数据
        mDataViewModel = ViewModelUtils.getViewModel(this, DataViewModel.class);

        MutableLiveData<String> addressEvent = mTestViewModel.getAddressEvent();
        //观察address值的变化
        addressEvent.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.e(TAG, "onChanged: s = " + s);
                mTvAddress.setText(s);
            }
        });

        //观察name值的变化
        mDataViewModel.getName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.e(TAG, "onChanged: nameChange = " + s);
                mTvName.setText(s);
            }
        });

        //观察age值的变化
        mDataViewModel.getAge().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.e(TAG, "onChanged: ageChange = " + String.valueOf(integer));
                mTvAge.setText(String.valueOf(integer));
            }
        });

        //观察user值的变化
        mDataViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                Log.e(TAG, "onChanged: userChange = " + user.getName() + "," + user.getAge());
                //转换 LiveData
                LiveData<String> userInfoLiveData = Transformations.map(mDataViewModel.getUser(), userInfo -> userInfo.getName() + " " + userInfo.getAge());
                userInfoLiveData.observe(MainActivity.this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        mDataViewModel.getUserInfo().setValue(s);
                    }
                });
            }
        });

        //观察userInfo值的变化
        mDataViewModel.getUserInfo().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.e(TAG, "onChanged: userInfoChange = " + s);
                mTvUserInfo.setText(s);
            }
        });

        //观察user值的变化
        UserLiveData.getInstance().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {

            }
        });

        //观察网络变化
        NetworkLiveData.getInstance(this).observe(this, new Observer<NetworkInfo>() {
            @Override
            public void onChanged(NetworkInfo networkInfo) {
                Log.e(TAG, "onChanged: networkInfo=" + networkInfo);
            }
        });

        /*
        mNetworkInfoObserver = new Observer<NetworkInfo>() {
            @Override
            public void onChanged(NetworkInfo networkInfo) {
                Log.e(TAG, "onChanged: networkInfo=" + networkInfo);
            }
        };
        NetworkLiveData.getInstance(this).observeForever(mNetworkInfoObserver);
         */
    }

    private void initView() {
        mTvAddress = findViewById(R.id.tv_address);
        mTvName = findViewById(R.id.tv_name);
        mTvAge = findViewById(R.id.tv_age);
        mTvUserInfo = findViewById(R.id.tv_user_info);
        mBtnModify = findViewById(R.id.btn_modify);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new TestFragment()).commit();
        mBtnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UpdateValueActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // NetworkLiveData.getInstance(this).removeObserver(mNetworkInfoObserver);
    }
}