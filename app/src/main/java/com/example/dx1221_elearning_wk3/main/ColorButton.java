package com.example.dx1221_elearning_wk3.main;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.widget.Button;

import com.example.dx1221_elearning_wk3.mgp2d.core.GameEntity;
import com.example.dx1221_elearning_wk3.mgp2d.core.Vector2;

public class ColorButton extends GameEntity
{

    Bitmap ButtonColor;

    public ColorButton(Bitmap bmp, Vector2 pos)
    {
        this._position = pos;
        ButtonColor = bmp;
    }

    @Override
    public void onRender(Canvas canvas)
    {
        canvas.drawBitmap(ButtonColor, 0 + _position.x, _position.y, null);

    }

    public boolean isClicked()
    {
        return TouchHandler.getInstance().Pressed() && isColliding(TouchHandler.getInstance());
    }
}
