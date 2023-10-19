package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.EditText;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.SignupnouActivityBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class LogInNouActivity extends AppCompatActivity {
    private EditText email ;
    private EditText password;
    private Button verificare,inregistrare;
    private ProgressBar progressbar;
    private FirebaseAuth mAuth;
    private EditText reconect;
//    private ImageButton viewPass;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginnou_activity);

        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        verificare = findViewById(R.id.Verificare);
        //reconect=findViewById(R.id.Reconect);
        inregistrare=findViewById(R.id.registration);
        //viewPass=findViewById(R.id.ViewPass);

        verificare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUserAccount();
            }
        });
        inregistrare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent
                        = new Intent( LogInNouActivity.this,SignUpNouActivity.class);

                startActivity(intent);
            }
        });

    }

    private void loginUserAccount(){
        //progressbar.setVisibility(View.VISIBLE);
        String emailVerfication,passwordVerification;
        emailVerfication=email.getText().toString();
        passwordVerification=password.getText().toString();
        if(TextUtils.isEmpty(emailVerfication)){
            Toast.makeText(getApplicationContext(),
                            "Please enter email!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(passwordVerification)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter password!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        mAuth.signInWithEmailAndPassword(emailVerfication,passwordVerification).
                addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            public void onComplete(@NonNull Task<AuthResult> task){
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),
                                                    "Login successful!!",
                                                    Toast.LENGTH_LONG)
                                            .show();

                                    // hide the progress bar
                                    progressbar.setVisibility(View.GONE);
                                    // if sign-in is successful
                                    // intent to home activity
                                    Intent intent
                                            = new Intent( LogInNouActivity.this,MainActivity.class);

                                    startActivity(intent);
                                }
                                else {


                                    // sign-in failed
                                    Toast.makeText(getApplicationContext(),
                                                    "Login failed!!",
                                                    Toast.LENGTH_LONG)
                                            .show();

                                    // hide the progress bar
                                    progressbar.setVisibility(View.GONE);
                                }
                            }
                        });
    }





}
