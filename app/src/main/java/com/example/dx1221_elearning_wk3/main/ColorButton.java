package com.example.dx1221_elearning_wk3.main;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.widget.Button;

import com.example.dx1221_elearning_wk3.mgp2d.core.GameEntity;
import com.example.dx1221_elearning_wk3.mgp2d.core.Vector2;

public class ColorButton extends GameEntity
{

    Bitmap ButtonColor;



    boolean isActive;

    public ColorButton(Bitmap bmp)
    {
        ButtonColor = bmp;
        isActive = false;
        _size = new Vector2(bmp.getWidth(), bmp.getHeight());
    }

    public void Spawn(Vector2 pos)
    {
        this._position = pos;
        isActive = true;
    }

    @Override
    public void onRender(Canvas canvas)
    {
        if(!isActive)
            return;
        canvas.drawBitmap(ButtonColor, _position.x, _position.y, null);

    }

    public boolean isClicked()
    {
        if(!isActive)
            return false;

        return TouchHandler.getInstance().Pressed() && isColliding(TouchHandler.getInstance());
    }
}
