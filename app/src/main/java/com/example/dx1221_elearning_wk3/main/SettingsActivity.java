package com.example.dx1221_elearning_wk3.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dx1221_elearning_wk3.R;

public class settingsActivity extends Activity implements View.OnClickListener
{
    private Button _backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingspage);
        _backButton = findViewById(R.id.back_btn);
        _backButton.setOnClickListener(this);
    }

    @Override
    public  void onClick(View v)
    {
        if (v == _backButton)
        {
            startActivity(new Intent(this, MainMenu.class));
        }
    }

}