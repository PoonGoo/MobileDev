package com.example.dx1221_elearning_wk3.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button; // Corrected import

import androidx.annotation.NonNull;

import com.example.dx1221_elearning_wk3.R;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameActivity;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameScene;
import com.google.android.gms.games.AuthenticationResult;
import com.google.android.gms.games.GamesSignInClient;
import com.google.android.gms.games.PlayGames;
import com.google.android.gms.games.PlayGamesSdk;
import com.google.android.gms.games.Player;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

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

        PlayGamesSdk.initialize(this);

        initGoogleClientAndSignIn();
    }

    private void initGoogleClientAndSignIn()
    {
        GamesSignInClient signInClient = PlayGames.getGamesSignInClient(this);
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

    public void ShowLeaderBoard()
    {


    }

    public void ShowAchievements()
    {

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

//536262799774-82o24mji2ea9jthnj3pd8rh365if3a7l.apps.googleusercontent.com (website client ID) //DO NOT TOUCH