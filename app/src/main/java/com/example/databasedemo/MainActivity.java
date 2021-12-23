package com.example.databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3))");

           /* myDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Priyank', 24)");
            myDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Jay', 32)");
            myDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Kishan', 19)");
            myDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Pranav', 22)");*/

            Cursor c = myDatabase.rawQuery("SELECT * FROM users", null);

            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");

            c.moveToFirst();

            while (c != null) {
                Log.i("name", c.getString(nameIndex));
                Log.i("age", Integer.toString(c.getInt(ageIndex)));

                c.moveToNext();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}