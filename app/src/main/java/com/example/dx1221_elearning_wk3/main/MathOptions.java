package com.example.dx1221_elearning_wk3.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.dx1221_elearning_wk3.R;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameActivity;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameEntity;
import com.example.dx1221_elearning_wk3.mgp2d.core.Vector2;

public class
MathOptions extends GameEntity
{
    private boolean isActive;
    private Paint numberPaint;

    public int optionNumber;

    private Bitmap borderBMP;
    public MathOptions(int optionNumber, Bitmap bmp)
    {
        isActive = false;
        numberPaint = new Paint();
        numberPaint.setTextSize(100);
        numberPaint.setColor(Color.WHITE);
        numberPaint.setTextAlign(Paint.Align.CENTER);
        this.optionNumber = optionNumber;

        borderBMP = bmp;
        _size = new Vector2(bmp.getWidth(), bmp.getHeight());

    }

    public void Spawn(Vector2 spawnPos)
    {

        isActive = true;
        _position = spawnPos;

    }
    @Override
    public void onRender(Canvas canvas)
    {
        canvas.drawBitmap(borderBMP, _position.x, _position.y,null);
        canvas.drawText(String.valueOf(optionNumber),_position.x + borderBMP.getWidth() * 0.5f, _position.y + borderBMP.getHeight() * 0.8f, numberPaint);


    }

    public boolean isClicked()
    {
        if(!isActive)
            return false;

        return TouchHandler.getInstance().Pressed() && isColliding(TouchHandler.getInstance());
    }
}
