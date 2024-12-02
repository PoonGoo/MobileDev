package com.example.dx1221_elearning_wk3.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dx1221_elearning_wk3.R;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameActivity;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameScene;

public class LoseMenu extends Activity implements View.OnClickListener
{
    private Button _restartBtn;
    private Button _mainMenuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.losemenu);
        _restartBtn = findViewById(R.id.restartBtn);
        _restartBtn.setOnClickListener(this);
        _mainMenuBtn = findViewById(R.id.mainMenuBtn);
        _mainMenuBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v == _restartBtn) {
            startActivity(new Intent(this, GameActivity.class));
        }
        else if(v == _mainMenuBtn)
        {
            startActivity(new Intent().setClass(this, MainMenu.class));
            GameScene.enter(MainGameScene.class);
        }
    }
}
