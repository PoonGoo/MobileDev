package com.example.dx1221_elearning_wk3.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.dx1221_elearning_wk3.R;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    private SeekBar musicSlider, soundSlider;
    private SharedPrefManager sharedPrefManager;
    private Button _backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingspage);

        sharedPrefManager = SharedPrefManager.getInstance();

        musicSlider = findViewById(R.id.music_slider);
        soundSlider = findViewById(R.id.sound_slider);
        _backButton = findViewById(R.id.back_btn);

        _backButton.setOnClickListener(this);

        int musicVolume = sharedPrefManager.readFromSharedPreferences(this, "settings", "music_volume");
        int soundVolume = sharedPrefManager.readFromSharedPreferences(this, "settings", "sound_volume");

        musicSlider.setProgress(musicVolume);
        soundSlider.setProgress(soundVolume);

        musicSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sharedPrefManager.writeToSharedPreferences(SettingsActivity.this, "settings", "music_volume", progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        soundSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sharedPrefManager.writeToSharedPreferences(SettingsActivity.this, "settings", "sound_volume", progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    @Override
    public void onClick(View v) {
        if (v == _backButton) {
            startActivity(new Intent(this, MainMenu.class)); // Go back to Main Menu
            finish(); // Close SettingsActivity
        }
    }
}
