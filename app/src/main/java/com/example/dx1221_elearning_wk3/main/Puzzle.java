package com.example.dx1221_elearning_wk3.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import com.example.dx1221_elearning_wk3.R;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameActivity;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameEntity;

public abstract class Puzzle
{
    Bitmap Background;

    boolean isActive;

    public Puzzle()
    {
        isActive = true;
    }

    public abstract void PlayPuzzle(double dt);
    public abstract void RandomizePuzzle();
    public void onRender(Canvas canvas)
    {
        canvas.drawBitmap(Background, 0 + Background.getWidth() * 0.2f , 0, null);
    }
}
