package com.example.firebasedemo_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firebasedemo_java.databinding.ActivityRegisterBinding;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button registerBtn;
    private ActivityRegisterBinding binding; //****
    private FirebaseAuth auth; // ****


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applyBinding();
        setContentView(binding.getRoot()); //****

        initializeDataField();

        setRegisterBtnOnClickListener();

    }

    private void initializeDataField() {
        emailEditText = binding.emailEditText;
        passwordEditText = binding.passwordEditText;
        registerBtn = binding.registerBtn;

        auth = FirebaseAuth.getInstance(); //****
    }

    private void applyBinding() {
        LayoutInflater layoutInflater = LayoutInflater.from(this); //****
        binding = ActivityRegisterBinding.inflate(layoutInflater); //****
    }

    private void setRegisterBtnOnClickListener() {
        registerBtn.setOnClickListener(view -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString();
            if (isCredentialsEmpty(email, password)) {
                showEmptyCredentialsToast();
            } else if (password.length() < 6) {
                showShortPasswordToast();
            } else {
                registerUser(email, password);
            }
        });
    }

    private void registerUser(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this,
                task -> {
                    if (task.isSuccessful()) {
                        showRegistrationSuccessfulToast();
                        startLoginActivity();
                    } else {
                        showRegistrationFailedToast();
                    }
                });
    }

    private void startLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void showRegistrationFailedToast() {
        Toast.makeText(this, "Registration failed.", Toast.LENGTH_SHORT).show();
    }

    private void showRegistrationSuccessfulToast() {
        Toast.makeText(this, "Registration is succesfull.", Toast.LENGTH_SHORT).show();
    }

    private void showShortPasswordToast() {
        Toast.makeText(this, "Password is less than 6 characters.", Toast.LENGTH_LONG).show();
    }

    private void showEmptyCredentialsToast() {
        Toast.makeText(this, "Empty credentials.", Toast.LENGTH_LONG).show();
    }

    private boolean isCredentialsEmpty(String email, String password) {
        return TextUtils.isEmpty(email) || TextUtils.isEmpty(password);  //****
    }
}