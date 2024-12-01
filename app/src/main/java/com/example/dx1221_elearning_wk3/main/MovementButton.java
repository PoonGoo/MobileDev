package com.example.dx1221_elearning_wk3.main;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.dx1221_elearning_wk3.mgp2d.core.GameEntity;
import com.example.dx1221_elearning_wk3.mgp2d.core.Vector2;

public class MovementButton extends GameEntity
{

    Bitmap bmp;
    public enum MovementType
    {
        RIGHT,
        LEFT
    }
    MovementType movementType;

    public MovementButton(Bitmap bmp, Vector2 position, MovementType movementType)
    {
        this.bmp = bmp;
        this._position = position;
        this.movementType = movementType;

        _size = new Vector2(bmp.getWidth(), bmp.getHeight());

    }

    @Override
    public void onRender(Canvas canvas) {
        canvas.drawBitmap(bmp, _position.x, _position.y, null);
    }

    public void Move(double dt, PlayerEntity player)
    {
        switch(movementType)
        {
            case LEFT:
                player.MoveLeft(dt);
                player.FlipLeft();
                break;
            case RIGHT:
                player.MoveRight(dt);
                player.FlipRight();
                break;
        }
    }



}
