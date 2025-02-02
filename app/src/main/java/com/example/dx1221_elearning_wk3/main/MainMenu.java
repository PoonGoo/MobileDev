package com.example.dx1221_elearning_wk3.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button; // Corrected import

import com.example.dx1221_elearning_wk3.R;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameActivity;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameScene;
import com.google.android.gms.games.GamesSignInClient;
import com.google.android.gms.games.PlayGames;
import com.google.android.gms.games.PlayGamesSdk;

public class MainMenu extends Activity implements View.OnClickListener // Corrected typo
{
    private Button _helpButton;
    private Button _settingsButton;
    private Button _startButton;

    private Button _leaderboardButton;
    private Button _achievementButton;


    private static GamesSignInClient signInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);

        _helpButton = findViewById(R.id.helpBtn);
        _helpButton.setOnClickListener(this);

        _settingsButton = findViewById(R.id.settingsBtn);
        _settingsButton.setOnClickListener(this);

        _leaderboardButton = findViewById(R.id.leaderboardbtn);
        _leaderboardButton.setOnClickListener(this);

        _achievementButton = findViewById(R.id.achievementbtn);
        _achievementButton.setOnClickListener(this);

        _startButton = findViewById(R.id.playBtn);
        _startButton.setOnClickListener(this);

        PlayGamesSdk.initialize(this);

        initGoogleClientAndSignIn();
    }

    private void initGoogleClientAndSignIn()
    {
        signInClient = PlayGames.getGamesSignInClient(this);
        signInClient.isAuthenticated().addOnCompleteListener(task ->
        {
             boolean isAuthenticated = task.isSuccessful() && task.getResult().isAuthenticated();

             if(isAuthenticated)
             {
                 PlayGames.getPlayersClient(this).getCurrentPlayer().addOnCompleteListener(task1 ->
                 {

                 });

             }

        });


    }

    private void SignIn()
    {
        signInClient.signIn().addOnCompleteListener(task -> {
            if(task.isSuccessful())
            {
                PlayGames.getPlayersClient(this);
            }
        });
    }

    public void ShowLeaderBoard()
    {
        PlayGames.getLeaderboardsClient(this).getLeaderboardIntent(getString(R.string.leaderboard)).addOnCompleteListener(task -> {
            if(task.isSuccessful())
            {
                Log.d("Leaderboard", "Leaderboard opened succ");
                startActivityForResult(task.getResult(), 0);

            }
            else {
                Log.d("Leaderboard", "Leaderboard opened failed");
                SignIn();

            }
        });

    }

    public void ShowAchievements()
    {
        PlayGames.getAchievementsClient(this).getAchievementsIntent().addOnCompleteListener(task -> {
            if(task.isSuccessful())
            {
                Log.d("Achievements", "Achievements opened succ");
                startActivityForResult(task.getResult(), 0);

            }
            else
            {
                Log.d("Achievements", "Achievements opened failed");
                SignIn();

            }
        });

    }

    @Override
    public void onClick(View v)
    {
        if(v == _settingsButton) {
            startActivity(new Intent(this, SettingsActivity.class));
        }
        else if(v == _leaderboardButton)
        {
            ShowLeaderBoard();
        }
        else if(v == _achievementButton)
        {
            ShowAchievements();
        }
        else if(v == _startButton)
        {
            startActivity(new Intent().setClass(this, GameActivity.class));
            GameScene.enter(MainGameScene.class);
        }
        else if(v == _helpButton)
        {
            startActivity(new Intent(this, AccordionActivity.class));
            GameScene.enter(MainGameScene.class);
        }
    }
}

//536262799774-82o24mji2ea9jthnj3pd8rh365if3a7l.apps.googleusercontent.com (website client ID) //DO NOT TOUCH