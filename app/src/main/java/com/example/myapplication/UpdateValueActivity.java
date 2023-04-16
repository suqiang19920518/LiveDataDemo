package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * 全局共享数据
 */
public class UpdateValueActivity extends AppCompatActivity {

    private EditText mEtName;
    private EditText mEtAge;

    public UserLiveData mUserLiveData;
    private DataViewModel mDataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_value);
        initView();
        mUserLiveData = new UserLiveData();
        mDataViewModel = ViewModelUtils.getViewModel(this, DataViewModel.class);
    }

    private void initView() {
        mEtName = findViewById(R.id.et_name);
        mEtAge = findViewById(R.id.et_age);
        mEtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                mDataViewModel.getName().setValue(charSequence.toString());
                if (!TextUtils.isEmpty(mEtAge.getText().toString()) && !TextUtils.isEmpty(charSequence.toString())) {
                    mDataViewModel.getUser().setValue(new User(charSequence.toString(), Integer.parseInt(mEtAge.getText().toString())));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEtAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                mDataViewModel.getAge().setValue(Integer.valueOf(charSequence.toString()));
                if (!TextUtils.isEmpty(mEtName.getText().toString()) && !TextUtils.isEmpty(charSequence.toString())) {
                    mDataViewModel.getUser().setValue(new User(mEtName.getText().toString(), Integer.parseInt(charSequence.toString())));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}