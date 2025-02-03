package com.example.dx1221_elearning_wk3.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dx1221_elearning_wk3.R;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameActivity;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameScene;
import com.google.android.gms.games.PlayGames;

public class LoseMenu extends Activity implements View.OnClickListener
{
    private Button _restartBtn;
    private Button _mainMenuBtn;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.losemenu);
        _restartBtn = findViewById(R.id.restartBtn);
        _restartBtn.setOnClickListener(this);
        _mainMenuBtn = findViewById(R.id.mainMenuBtn);
        _mainMenuBtn.setOnClickListener(this);

        // Retrieve and display score
        MainGameScene.StopBackgroundMusic();
        Log.d("EndGame", "ended game");

        TextView _scoreText = findViewById(R.id.scoreText);
        int finalScore = getIntent().getIntExtra("finalScore", 0); // Default to 0 if no score is passed
        PlayGames.getLeaderboardsClient(this).submitScore(getString(R.string.leaderboard), finalScore);
        if(finalScore >= 200)
        {
            PlayGames.getAchievementsClient(this).unlock(getString(R.string.novice_achivement));
        }
        _scoreText.setText("Your Score: " + finalScore);

    }

    @Override
    public void onClick(View v)
    {
        if(v == _restartBtn)
        {
            MainGameScene.StopBackgroundMusic();
            startActivity(new Intent(this, GameActivity.class));
        }
        else if(v == _mainMenuBtn)
        {
            MainGameScene.StopBackgroundMusic();
            startActivity(new Intent().setClass(this, MainMenu.class));
            GameScene.enter(MainGameScene.class);
        }
    }
}
