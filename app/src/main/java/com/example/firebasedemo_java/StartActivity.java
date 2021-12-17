package com.example.firebasedemo_java;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firebasedemo_java.databinding.ActivityStartBinding;

public class StartActivity extends AppCompatActivity {
    private Button registerBtn;
    private Button loginBtn;
    private ActivityStartBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applyBinding();
        setContentView(binding.getRoot());

        initializeDataField();
        setOnClickListeners();

    }

    private void initializeDataField() {
        registerBtn = binding.registerButton;
        loginBtn = binding.loginButton;
    }

    private void applyBinding() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        binding = ActivityStartBinding.inflate(layoutInflater);
    }

    private void setOnClickListeners() {
        Log.v("RegisterBtn", "onClickListener");
        setRegisterBtnOnClickListener();
        setLoginBtnOnClickListener();
    }

    private void setLoginBtnOnClickListener() {
        loginBtn.setOnClickListener(view -> { //****
            startActivity(new Intent(StartActivity.this, LoginActivity.class));
//            finish(); // Do not go back here once back button is clicked.
        });

    }

//    private void setRegisterBtnOnClickListener() {
//        registerBtn.setOnClickListener(view -> { //****
//                view.getContext = StartActivity.this
//            (view.getContext()).startActivity(new Intent(view.getContext(), RegisterActivity.class));
//            finish();
//        });
//
//    }

    private void setRegisterBtnOnClickListener() {
        registerBtn.setOnClickListener(view -> { //****
            startActivity(new Intent(StartActivity.this, RegisterActivity.class));
//            finish();
        });

    }


}