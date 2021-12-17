package com.example.firebasedemo_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firebasedemo_java.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginBtn;
    private ActivityLoginBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applyBinding();
        setContentView(binding.getRoot());

        initializeDataField();
        setLoginBtnOnClickListener();


    }

    private void applyBinding() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        binding = ActivityLoginBinding.inflate(layoutInflater);
    }

    private void setLoginBtnOnClickListener() {
        loginBtn.setOnClickListener(view -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString();
            loginUser(email, password);
        });
    }

    private void loginUser(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                showLoginSuccesfulToast();
                startMainActivity();
            } else {
                showLoginFailedToast();
            }
        });
    }

    private void startMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void showLoginFailedToast() {
        Toast.makeText(this, "Login failed. Wrong credentials.", Toast.LENGTH_LONG).show();
    }

    private void showLoginSuccesfulToast() {
        Toast.makeText(this, "Login succesful.", Toast.LENGTH_LONG).show();
    }

    private void initializeDataField() {
        emailEditText = binding.emailEditText;
        passwordEditText = binding.passwordEditText;
        loginBtn = binding.loginBtn;
        auth = FirebaseAuth.getInstance();
    }
}