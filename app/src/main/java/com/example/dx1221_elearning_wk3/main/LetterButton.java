package com.example.dx1221_elearning_wk3.main;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.dx1221_elearning_wk3.mgp2d.core.GameEntity;
import com.example.dx1221_elearning_wk3.mgp2d.core.Vector2;

public class LetterButton extends GameEntity
{

    private Paint wordTxt;

    Bitmap ballContainer;

    private char letter;

    public LetterButton(Bitmap bmp, char character)
    {
        wordTxt = new Paint();
        wordTxt.setTextSize(100);
        wordTxt.setTextAlign(Paint.Align.CENTER);
        wordTxt.setColor(Color.BLACK);
        this.ballContainer = bmp;
        this.letter = character;
        _size = new Vector2(ballContainer.getWidth(), ballContainer.getHeight());

    }

    public void Spawn(Vector2 spawnPos)
    {
        this._position = spawnPos;
    }

    @Override
    public void onRender(Canvas canvas)
    {
        canvas.drawBitmap(ballContainer,_position.x, _position.y, null);
        canvas.drawText(String.valueOf(letter), _position.x, _position.y, wordTxt);
    }
}
