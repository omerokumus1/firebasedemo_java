package com.example.firebasedemo_java;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firebasedemo_java.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Button logoutButton;
    private ActivityMainBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applyBinding();
        setContentView(binding.getRoot());

        initializeDataField();
        setLogoutBtnOnClickListener();



    }
    /* Adding data to firebase
        FirebaseDatabase.getInstance().getReference().child("Branch Name").child("Sub Branch Name").setValue("Branch value");

     * Adding multiple data to firebase
        HashMap<String, Object> map = new HashMap<>();
        map.put("Sub Branch Name", "value");
        map.put("Sub Branch Name2", "value2");
        FirebaseDatabase.getInstance().getReference().child("Branch Name").updateChildren(map);
        Branch = Objects in JSON.
        Sub branch = data field in JSON.

     * Create branch with unique id: push()
        FirebaseDatabase.getInstance().getReference().child("Branch Name").push().child("Sub Branch Name").setValue("Branch value");

     */

    /* Reading data from firebase
        ArrayList<String> arrayList = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Branch Name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    arrayList.add(snapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        * To put them into ListView go to https://www.youtube.com/watch?v=SV9pJqR41KI at 40:00

     */

    /* Retrieving set of information from firebase
        Create a class which will hold the retrieved data
        public class Information{
            String email;
            String name;
            // getters setters and constructors
        }
        ** data field names must be the same as in firebase

        ArrayList<String> arrayList = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Branch Name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                **  Information info = snapshot.getValue(Information.class);
                **  String txt = info.getName() + ": " + info.getEmail();
                **  arrayList.add(txt);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


     */

    private void setLogoutBtnOnClickListener() {
        logoutButton.setOnClickListener(view -> {
            showLogoutToast();
            auth.signOut();
            startLoginActivity();
        });
    }

    private void startLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void showLogoutToast() {
        Toast.makeText(this, "Logged out!", Toast.LENGTH_SHORT).show();
    }

    private void initializeDataField() {
        logoutButton = binding.logoutButton;
        auth = FirebaseAuth.getInstance();
    }

    private void applyBinding() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        binding = ActivityMainBinding.inflate(layoutInflater);
    }
}