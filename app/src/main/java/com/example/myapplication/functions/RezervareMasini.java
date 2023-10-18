package com.example.myapplication.functions;

import java.util.Calendar;
import com.example.myapplication.*;

public class RezervareMasini {

    private int[][] disponibilitate;

    public RezervareMasini() {
        disponibilitate = new int[7][32]; // Inițial toate valorile sunt 0 (disponibil)
    }

    public boolean isIntervalDisponibil(int ziua, double oraInceput, double oraSfarsit) {
        int indexOraInceput = (int) ((oraInceput - 8) * 2); // Transformăm ora în index
        int indexOraSfarsit = (int) ((oraSfarsit - 8) * 2); // Transformăm ora în index
        indexOraInceput = Math.round(indexOraInceput);
        indexOraSfarsit = Math.round(indexOraSfarsit);

        for (int i = indexOraInceput; i < indexOraSfarsit; i++) {
            if (disponibilitate[ziua][i] == 1) {
                return false; // Intervalul este ocupat
            }
        }

        return true; // Intervalul este disponibil
    }

    public void programeazaInterval(int ziua, double oraInceput, double oraSfarsit) {
        int indexOraInceput = (int) ((oraInceput - 8) * 2); // Transformăm ora în index
        int indexOraSfarsit = (int) ((oraSfarsit - 8) * 2); // Transformăm ora în index
        indexOraInceput = Math.round(indexOraInceput);
        indexOraSfarsit = Math.round(indexOraSfarsit);

        for (int i = indexOraInceput; i < indexOraSfarsit; i++) {
            disponibilitate[ziua][i] = 1; // Marcam intervalul ca ocupat
        }
    }
}
