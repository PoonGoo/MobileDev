package com.example.dx1221_elearning_wk3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button; // Corrected import

public class MainMenu extends Activity implements View.OnClickListener // Corrected typo
{
    private Button _helpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);
        _helpButton = findViewById(R.id.helpBtn);
        _helpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v == _helpButton) {
            startActivity(new Intent(this, HelpPage.class));
        }
    }
}
