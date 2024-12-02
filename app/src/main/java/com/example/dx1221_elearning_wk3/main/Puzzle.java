package com.example.dx1221_elearning_wk3.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.dx1221_elearning_wk3.R;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameActivity;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameEntity;

public abstract class Puzzle extends GameEntity
{
    Bitmap Background;

    boolean isActive;

    public Puzzle()
    {
        isActive = true;
        Bitmap Backgroundbmp = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.warning_sign);
        Background = Bitmap.createScaledBitmap(Backgroundbmp, (int)(MainGameScene.screenWidth * 0.8f), (int)(MainGameScene.screenHeight * 0.8f), true   );
    }

    public abstract void PlayPuzzle(double dt);

    @Override
    public void onRender(Canvas canvas) {
        canvas.drawBitmap(Background, MainGameScene.screenWidth + MainGameScene.screenWidth * 0.2f , MainGameScene.screenHeight + MainGameScene.screenHeight * 0.2f, null);
    }
}
