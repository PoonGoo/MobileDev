package com.example.dx1221_elearning_wk3.main;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.dx1221_elearning_wk3.mgp2d.core.GameEntity;
import com.example.dx1221_elearning_wk3.mgp2d.core.Vector2;

public class
MathOptions extends GameEntity
{
    private boolean isActive;
    private Paint numberPaint;

    private int optionNumber;
    public MathOptions(int optionNumber)
    {
        isActive = false;
        numberPaint = new Paint();
        numberPaint.setTextSize(100);
        numberPaint.setColor(Color.WHITE);
        numberPaint.setTextAlign(Paint.Align.CENTER);
        this.optionNumber = optionNumber;

        _size = new Vector2(numberPaint.getTextScaleX(), numberPaint.getTextScaleX());

    }

    public void Spawn(Vector2 spawnPos)
    {

        isActive = true;
        _position = spawnPos;

    }
    @Override
    public void onRender(Canvas canvas)
    {

        canvas.drawText(String.valueOf(optionNumber),_position.x + _size.x, _position.y + _size.y, numberPaint);
    }

    public boolean isClicked()
    {
        if(!isActive)
            return false;

        return TouchHandler.getInstance().Pressed() && isColliding(TouchHandler.getInstance());
    }
}
