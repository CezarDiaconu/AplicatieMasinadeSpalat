package com.example.myapplication;

import static org.xmlpull.v1.XmlPullParser.TEXT;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "app.db";
    public static final String TABLE_NAME = "users";
    public static final String COL_1 = "id";
    public static final String COL_2 = "firstname";
    public static final String Col_3 = "middlename";
    public static final String Col_4 = "lastname";
    public static final String Col_5 = "email";
    public static final String Col_6 = "password";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "firstname TEXT, middlename TEXT, lastname TEXT, email TEXT, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String firstname, String middlename, String lastname, String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,firstname);
        contentValues.put(Col_3,middlename);
        contentValues.put(Col_4,lastname);
        contentValues.put(Col_5,email);
        contentValues.put(Col_6,password);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1)
            return false;
        else return true;
    }
}
