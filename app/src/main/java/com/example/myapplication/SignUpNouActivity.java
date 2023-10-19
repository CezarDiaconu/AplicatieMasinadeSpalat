package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
public class SignUpNouActivity extends AppCompatActivity {

    private EditText emailTextView, passwordTextView;
    private Button Btn,back;
    private ProgressBar progressbar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupnou_activity);

        // taking FirebaseAuth instance
        mAuth = FirebaseAuth.getInstance();

        // initialising all views through id defined above
        emailTextView = findViewById(R.id.email);
        passwordTextView = findViewById(R.id.passwd);
        Btn = findViewById(R.id.back);
        progressbar = findViewById(R.id.progressbar);

        // Set on Click Listener on Registration button
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                registerNewUser();
            }
        });
    }

    private void registerNewUser()
    {

        // show the visibility of progress bar to show loading
        progressbar.setVisibility(View.VISIBLE);

        // Take the value of two edit texts in Strings
        String email, password;
        email = emailTextView.getText().toString();
        password = passwordTextView.getText().toString();

        // Validations for input email and password
        if (TextUtils.isEmpty(email)  || !isValidEmail(email)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter email!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(password) || !isPasswordValid(password)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter password!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }

        // create new user or register new user
        mAuth
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),
                                            "Registration successful!",
                                            Toast.LENGTH_LONG)
                                    .show();

                            // hide the progress bar
                            progressbar.setVisibility(View.GONE);

                            // if the user created intent to login activity
                            Intent intent
                                    = new Intent(SignUpNouActivity.this,
                                    LogInNouActivity.class);
                            startActivity(intent);
                        }
                        else {

                            // Registration failed
                            Toast.makeText(
                                            getApplicationContext(),
                                            "Registration failed!!"
                                                    + " Please try again later",
                                            Toast.LENGTH_LONG)
                                    .show();

                            // hide the progress bar
                            progressbar.setVisibility(View.GONE);
                        }
                    }
                });
    }
    private boolean isValidEmail(String email) {

        String emailPattern = "[a-zA-Z0-9._-]+@student.unitbv.ro";
        return email.matches(emailPattern);
    }


    private String getPasswordValidity(String password) {
        StringBuilder message = new StringBuilder();

        if (password.length() < 8) {
            message.append("Parola trebuie să conțină cel puțin 8 caractere.\n");
        }
        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            message.append("Parola trebuie să conțină cel puțin un caracter special.\n");
        }
        if (!password.matches(".*[A-Z].*")) {
            message.append("Parola trebuie să conțină cel puțin o literă mare.\n");
        }
        if (!password.matches(".*[a-z].*")) {
            message.append("Parola trebuie să conțină cel puțin o literă mică.\n");
        }
        if (!password.matches(".*\\d.*")) {
            message.append("Parola trebuie să conțină cel puțin o cifră.\n");
        }

        return message.toString();
    }
    private boolean isPasswordValid(String password) {

        String validityMessage = getPasswordValidity(password);
        if (validityMessage.isEmpty()) {

            return true;
        } else {

            return false;
        }
    }
}