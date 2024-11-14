package com.example.dx1221_elearning_wk3.mgp2d.core;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.dx1221_elearning_wk3.R;

public class MainGameScene extends GameScene{
    private Bitmap _backgroundBitmap;
    private Bitmap _backgroundBitmap1;
    private float _backgroundPosition;
    private int screenWidth;

    @Override
    public void onCreate() {
        super.onCreate();
        int screenHeight = GameActivity.instance.getResources().getDisplayMetrics().heightPixels;
        screenWidth = GameActivity.instance.getResources().getDisplayMetrics().widthPixels;

        Bitmap bmp = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.gamescene);
        _backgroundBitmap = Bitmap.createScaledBitmap(bmp, screenWidth,screenHeight,true);
        _backgroundBitmap1 = Bitmap.createScaledBitmap(bmp, screenWidth,screenHeight,true);

    }

    @Override
    public void onUpdate(float dt) {
        _backgroundPosition = (_backgroundPosition - dt * 500f) % (float)screenWidth;
    }

    @Override
    public void onRender(Canvas canvas) {
    canvas.drawBitmap(_backgroundBitmap, _backgroundPosition, 0, null );
    canvas.drawBitmap(_backgroundBitmap1, _backgroundPosition + screenWidth, 0, null );
    }
}
