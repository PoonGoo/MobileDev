package com.example.dx1221_elearning_wk3.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.provider.MediaStore;

import com.example.dx1221_elearning_wk3.R;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameActivity;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameEntity;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameScene;

import java.util.Vector;

public class MainGameScene extends GameScene {
    private Bitmap _backgroundBitmap;
    private Bitmap _backgroundBitmap1;
    private float _backgroundPosition;
    private int screenWidth;
    private int screenHeight;

    public Bitmap leftArrow;

    public Bitmap rightArrow;

    Vector<GameEntity> _gameEntities = new Vector<>();
    private MediaPlayer _ringSound;
    private MediaPlayer _bgMusic;
    private Vibrator _vibrator;

    @Override
    public void onCreate() {
        super.onCreate();
        screenHeight = GameActivity.instance.getResources().getDisplayMetrics().heightPixels;
        screenWidth = GameActivity.instance.getResources().getDisplayMetrics().widthPixels;

        Bitmap bmp = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.gamescene);
        _backgroundBitmap = Bitmap.createScaledBitmap(bmp, screenWidth,screenHeight,true);
        _backgroundBitmap1 = Bitmap.createScaledBitmap(bmp, screenWidth,screenHeight,true);


        Bitmap lArrow  = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.left_button);
        Bitmap rArrow = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.right_button);
        leftArrow = Bitmap.createScaledBitmap(lArrow, (int)(screenHeight * 0.2f) ,(int)(screenHeight * 0.2f),true);
        rightArrow = Bitmap.createScaledBitmap(rArrow, (int)(screenHeight * 0.2f) ,(int)(screenHeight * 0.2f),true);

                _gameEntities.add(new BackgroundEntity());
        _gameEntities.add(new PlayerEntity());
        
        _bgMusic = MediaPlayer.create(GameActivity.instance.getApplicationContext(), R.raw.shinytech);
        _bgMusic.setLooping(true);
/*
        _bgMusic.setVolume();
*/
        _bgMusic.start();
    }

    @Override
    public void onUpdate(float dt) {
        _backgroundPosition = (_backgroundPosition - dt * 500f) % (float)screenWidth;

        for(GameEntity entity : _gameEntities)
        {
            entity.onUpdate(dt);
        }
    }

    @Override
    public void onRender(Canvas canvas) {
        canvas.drawBitmap(_backgroundBitmap, _backgroundPosition, 0, null );
        canvas.drawBitmap(_backgroundBitmap1, _backgroundPosition + screenWidth, 0, null );
        canvas.drawBitmap(leftArrow, screenWidth * 0.1f, screenHeight * 0.7f, null);
        canvas.drawBitmap(rightArrow, screenWidth * 0.2f, screenHeight * 0.7f, null);


        for(GameEntity entity : _gameEntities)
        {
            entity.onRender(canvas);
        }
    }
}
