package com.example.sharedpreferencesdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String Shared_Pref = "name";
    private static final String Key_name = "key";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //there is a class name Shared preferences
        SharedPreferences sharedPreferences = this.getSharedPreferences(Shared_Pref, MODE_PRIVATE);

        // write into Shared preferences
        sharedPreferences.edit().putString(Key_name, "Eveneet").apply();

        //Read from SharedPreFerences
        String name = sharedPreferences.getString(Key_name,"Kulvir");

        Log.i(TAG, "onCreate: " + name);

        // save an array
        ArrayList<String> names = new ArrayList<>(Arrays.asList("Ankita", "Karan","Sandy"));
        sharedPreferences.edit().putStringSet("names", new HashSet<String>(names)).apply();
//
//        //retrieve Data
//        Set<String> recvNames = sharedPreferences.getStringSet("names", new HashSet<String>());
//        Log.i(TAG, "onCreate: " + recvNames.toString());
//
        //write an object into Shared pref

        try
        {
            sharedPreferences.edit().putString("names", ObjectSerializer.serialize(names)).apply();
            Log.i(TAG,"onCreate: "+ObjectSerializer.serialize(names));

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        List<String> recvNames =new ArrayList<>();
        try
        {

           recvNames = (ArrayList) (sharedPreferences.getString("names", ObjectSerializer.serialize(new ArrayList<>());
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        Log.i("onCreate: "+recvNames.toString());


    }
}
