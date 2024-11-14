package com.example.dx1221_elearning_wk3;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class SplashPage extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashpage);
        Thread splashThread = new Thread()
        {
            @Override
            public void run()
            {
                super.run();
                try {
                    sleep(3000);
                    startActivity(new Intent(SplashPage.this, MainMenu.class));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        splashThread.start();
    }

}
