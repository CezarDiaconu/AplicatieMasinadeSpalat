package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;

public class CamineActivity extends AppCompatActivity {

    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camineactivity);

        radioGroup = findViewById(R.id.radioGroup);

        Button backbtn1 = findViewById(R.id.backbtn1);
        Button rezervabtn = findViewById(R.id.rezervabtn);

        backbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CamineActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        rezervabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                if (selectedRadioButtonId != -1) {
                    RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                    int radioButtonNumber = getRadioButtonNumber(selectedRadioButton);

                    Intent intent = new Intent(CamineActivity.this, MasiniActivity.class);
                    intent.putExtra("selectedRadioButton", radioButtonNumber);
                    startActivity(intent);
                } else {
                    Toast.makeText(CamineActivity.this, "No button selected", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private int getRadioButtonNumber(RadioButton radioButton) {
        // You can implement a logic here to determine the number of the radio button.
        // For example, you can use the text of the radio button.
        if (radioButton.getId() == R.id.masina1) {
            return 1;
        } else if (radioButton.getId() == R.id.masina2) {
            return 2;
        } else if (radioButton.getId() == R.id.masina3) {
            return 3;
        } else if (radioButton.getId() == R.id.masina4) {
            return 4;
        } else {
            return 0; // Default or error case
        }
    }
}
