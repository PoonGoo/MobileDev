package com.example.dx1221_elearning_wk3.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.example.dx1221_elearning_wk3.R;

public class SplashPage extends Activity {

    private ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashpage);

        progressBar = findViewById(R.id.progressBar);

        Thread splashThread = new Thread() {
            @Override
            public void run() {
                super.run();
                int progress = 0;
                while (progress < 100) {
                    try {
                        sleep(30);
                        progress += 1;
                        progressBar.setProgress(progress);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                startActivity(new Intent(SplashPage.this, MainMenu.class));
                finish();
            }
        };

        splashThread.start();
    }
}
