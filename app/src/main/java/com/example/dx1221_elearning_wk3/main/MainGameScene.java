package com.example.dx1221_elearning_wk3.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;

import com.example.dx1221_elearning_wk3.R;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameActivity;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameEntity;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameScene;
import com.example.dx1221_elearning_wk3.mgp2d.core.Vector2;

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

    private PlayerEntity player;




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
         player = new PlayerEntity();
        _gameEntities.add(new BackgroundEntity());
        _gameEntities.add(new TouchHandler());
        _gameEntities.add(player);
        _gameEntities.add(new MovementButton(leftArrow, new Vector2(screenWidth * 0.1f, screenHeight * 0.7f), MovementButton.MovementType.LEFT));
        _gameEntities.add(new MovementButton(rightArrow, new Vector2(screenWidth * 0.2f, screenHeight * 0.7f), MovementButton.MovementType.RIGHT));

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
            if(entity instanceof TouchHandler)
            {
                for(GameEntity other : _gameEntities)
                {
                    if(other instanceof MovementButton && entity.isColliding(other))
                    {
                        Log.d("MovementButton", "Movement Button Pressed");
                        if(((TouchHandler) entity).Pressed())
                        {
                            ((MovementButton) other).Move(dt,player);
                        }
                    }

                }

                if((((TouchHandler) entity).Pressed() || ((TouchHandler) entity).SecondPressed()) && (((TouchHandler) entity).SecondTouchPos.x >= screenWidth / 2f))
                {
                    Log.d("Fly", "FlyPressed");
                    player.Fly(dt);
                }
                else
                {
                    player.EndFly();
                }


            }
        }
    }

    @Override
    public void onRender(Canvas canvas) {
        canvas.drawBitmap(_backgroundBitmap, _backgroundPosition, 0, null );
        canvas.drawBitmap(_backgroundBitmap1, _backgroundPosition + screenWidth, 0, null );

        for(GameEntity entity : _gameEntities)
        {
            entity.onRender(canvas);

        }
    }
}
