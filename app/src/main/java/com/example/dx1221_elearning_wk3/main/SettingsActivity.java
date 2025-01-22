package com.example.dx1221_elearning_wk3.main;

import android.content.SharedPreferences;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dx1221_elearning_wk3.R;

public class SettingsActivity extends AppCompatActivity {
    private SeekBar musicSlider;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingspage); // Link your settings XML layout

        // SharedPreferences setup
        sharedPreferences = getSharedPreferences("GameSettings", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // Initialize music slider
        musicSlider = findViewById(R.id.music_slider);
        float savedVolume = sharedPreferences.getFloat("MusicVolume", 0.5f); // Load saved volume (default 50%)
        musicSlider.setProgress((int) (savedVolume * 100));

        // Save volume changes
        musicSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float volume = progress / 100f;
                editor.putFloat("MusicVolume", volume);
                editor.apply();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Back button logic
        findViewById(R.id.back_btn).setOnClickListener(v -> finish());
    }
}
