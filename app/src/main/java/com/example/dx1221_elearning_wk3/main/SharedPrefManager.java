package com.example.dx1221_elearning_wk3.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SharedPrefManager {
    private static SharedPrefManager instance;

    private SharedPrefManager() {}

    public static synchronized SharedPrefManager getInstance() {
        if (instance == null)
            instance = new SharedPrefManager();
        return instance;
    }

    public void writeToSharedPreferences(Context context, String filename, String key, int value) {
        SharedPreferences sharedPref = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public int readFromSharedPreferences(Context context, String filename, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
        return sharedPref.getInt(key, 50);
    }

    public String readFromAssets(Context context, String filename) {
        StringBuilder result = new StringBuilder();
        try {
            InputStream inputStream = context.getAssets().open(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
        } catch (IOException e) {
            Log.e("ERROR", "readFromAssets: ", e);
        }
        return result.toString();
    }
}
