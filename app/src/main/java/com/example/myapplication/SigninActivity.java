package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SigninActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText firstname;
    EditText middlename;
    EditText lastname;
    EditText email;
    EditText password;
    Button signinbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signinactivity);
        myDb = new DatabaseHelper(this);

        firstname = findViewById(R.id.firstname);
        middlename = findViewById(R.id.middlename);
        lastname = findViewById(R.id.lastname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signinbtn = findViewById(R.id.signinbtn);
        AddData();
    }

    public void AddData() {
        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(
                        firstname.getText().toString(),
                        middlename.getText().toString(),
                        lastname.getText().toString(),
                        email.getText().toString(),
                        password.getText().toString()
                );
                if (isInserted) {
                    Toast.makeText(SigninActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(SigninActivity.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
