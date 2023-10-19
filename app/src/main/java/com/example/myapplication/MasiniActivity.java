package com.example.myapplication;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.functions.RezervareMasini;

public class MasiniActivity extends AppCompatActivity {
    private RezervareMasini rezervareMasini;
    private String durata = "";
    private String ziua = "";
    double ora = 0;
    double oraf = 0;

    int ziuaValue=-1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.masiniactivity);

        int selectedRadioButton = getIntent().getIntExtra("selectedRadioButton", 0);

        // Find the TextView in your layout by its id
        TextView textView = findViewById(R.id.textView);

        // Set the value in the TextView
        textView.setText("Ai selectat masina: " + selectedRadioButton);
        rezervareMasini = new RezervareMasini();

        RadioGroup zileGroup = findViewById(R.id.zileGroup);
        zileGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup View, int checkedId) {
                RadioButton selectedRadioButton = findViewById(checkedId);
                if (selectedRadioButton != null) {
                    ziua = selectedRadioButton.getText().toString();
                }

                Toast.makeText(MasiniActivity.this, "Selected day: " + ziua, Toast.LENGTH_SHORT).show();
            }
        });

        RadioGroup intervalGroup = findViewById(R.id.intervalGroup);
        intervalGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup View, int checkedId) {
                RadioButton selectedRadioButton = findViewById(checkedId);
                durata = "";
                if (selectedRadioButton != null) {
                    durata = selectedRadioButton.getText().toString();
                }

                Toast.makeText(MasiniActivity.this, "Durata intervalului: " + durata, Toast.LENGTH_SHORT).show();
            }
        });

        TextView oraProgramarii = findViewById(R.id.oraProgramarii);

        // Programare Interval
        Button programeazaBtn = findViewById(R.id.programeazaBtn);
        programeazaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ora = Double.parseDouble(oraProgramarii.getText().toString());
                if(oraf == 0) {
                    if (durata.equals("30 min")) {
                        oraf = (ora + 0.5);
                    } else if (durata.equals("1 ora")) {
                        oraf = (ora + 2);
                    } else if (durata.equals("2 ore")) {
                        oraf = (ora + 2);
                    }

                    ziuaValue = -1;
                    switch (ziua) {
                        case "Luni":
                            ziuaValue = 0;
                            break;
                        case "Marti":
                            ziuaValue = 1;
                            break;
                        case "Miercuri":
                            ziuaValue = 2;
                            break;
                        case "Joi":
                            ziuaValue = 3;
                            break;
                        case "Vineri":
                            ziuaValue = 4;
                            break;
                        case "Sambata":
                            ziuaValue = 5;
                            break;
                        case "Duminica":
                            ziuaValue = 6;
                            break;
                        default:
                            // Poți trata aici cazurile în care ziua nu este recunoscută sau eroare
                            break;
                    }

                    if (ziuaValue != -1 && rezervareMasini.isIntervalDisponibil(ziuaValue, ora, oraf)) {
                        rezervareMasini.programeazaInterval(ziuaValue, ora, oraf);
                        Toast.makeText(MasiniActivity.this, "Programarea a fost facuta!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MasiniActivity.this, "Intervalul este ocupat!", Toast.LENGTH_SHORT).show();
                    }
                    Log.d("Debug", "ziua: " + ziua);
                    Log.d("Debug", "durata: " + durata);
                    Log.d("Debug", "ora: " + ora);
                    Log.d("Debug", "oraf: " + oraf);
                    Log.d("Debug", "ziuaValue: " + ziuaValue);
                }
                else{
                    Toast.makeText(MasiniActivity.this, "Aveti deja o programare facuta!", Toast.LENGTH_SHORT).show();
                }
                };
    });
        //Programare Interval


        // Anulare Interval Programat
        Button anuleazaProgramareabtn = findViewById(R.id.anuleazaprogramareabtn);
        anuleazaProgramareabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ziuaValue != -1 && rezervareMasini.isIntervalDisponibil(ziuaValue, ora, oraf) == false) {
                    rezervareMasini.anuleazaInterval(ziuaValue, ora, oraf);
                    oraf = 0;
                    Toast.makeText(MasiniActivity.this, "Intevalul a fost anulat", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MasiniActivity.this, "Nu aveti o programare facuta", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Anulare Interval Programat
}
}