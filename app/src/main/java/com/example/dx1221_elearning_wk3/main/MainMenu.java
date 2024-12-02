package com.example.dx1221_elearning_wk3.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button; // Corrected import

import com.example.dx1221_elearning_wk3.R;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameActivity;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameScene;

public class MainMenu extends Activity implements View.OnClickListener // Corrected typo
{
    private Button _helpButton;
    private Button _startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);
        _helpButton = findViewById(R.id.settingsBtn);
        _helpButton.setOnClickListener(this);
        _startButton = findViewById(R.id.playBtn);
        _startButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v == _helpButton) {
            startActivity(new Intent(this, HelpPage.class));
        }
        else if(v == _startButton)
        {
            startActivity(new Intent().setClass(this, GameActivity.class));
            GameScene.enter(MainGameScene.class);
        }
    }
}
