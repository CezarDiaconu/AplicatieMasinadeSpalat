package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class LoginActivity extends AppCompatActivity {
    // Define your database connection parameters
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);
        myDb = new DatabaseHelper(this);

        Button loginbtn = findViewById(R.id.loginbtn);
        Button gotosignin = findViewById(R.id.gotosignin);
        String userEmail = getIntent().getStringExtra("email");
        String userPassword = getIntent().getStringExtra("password");

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(LoginActivity.this, "Email: " + userEmail + "\nPassword: " + userPassword, Toast.LENGTH_LONG).show();
            }
        });
        gotosignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SigninActivity.class);
                startActivity(intent);

            }
        });


    }
}