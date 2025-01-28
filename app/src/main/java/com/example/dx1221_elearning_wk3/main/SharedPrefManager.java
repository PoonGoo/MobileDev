package com.example.dx1221_elearning_wk3.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.dx1221_elearning_wk3.mgp2d.core.GameActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SharedPrefManager
{
    public static SharedPrefManager instance;

    public static synchronized SharedPrefManager getInstance() {
        if (instance == null)
            instance = new SharedPrefManager();

        return instance;
    }

    public void writeToSharedPreferences(String filename, String key, int value)
    {
        SharedPreferences sharedPref =
                GameActivity.instance.getSharedPreferences(filename, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public int readFromSharedPreferences(String filename, String key)
    {
        SharedPreferences sharedpref =
                GameActivity.instance.getSharedPreferences(filename, Context.MODE_PRIVATE);
        return sharedpref.getInt(key, 0);
    }

    public String readFromAssets(String filename) {
        StringBuilder result = new StringBuilder();
        try
        {
            InputStream inputStream = GameActivity.instance.getAssets().open(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = reader.readLine();
            while(line != null) {
                result.append(line);
                line = reader.readLine();
            }
            reader.close();
        }

        catch (IOException e)
        {
            Log.e("ERROR", "readFromAssets: ", e);
        }

        return result.toString();
    }

}
