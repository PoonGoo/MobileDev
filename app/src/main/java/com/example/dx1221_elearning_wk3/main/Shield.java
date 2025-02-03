package com.example.dx1221_elearning_wk3.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.dx1221_elearning_wk3.R;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameActivity;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameEntity;

public class Shield extends GameEntity
{
    public static Bitmap bmp;
public static Bitmap scaledBMP;
    public Shield()
    {
        if(bmp == null)
        {
            bmp = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.shield);
            scaledBMP = Bitmap.createScaledBitmap(bmp, (int) (bmp.getWidth() * 0.09f), (int) (bmp.getHeight() * 0.09f), true);
        }
    }

    @Override
    public void onUpdate(float dt) {
        super.onUpdate(dt);
    }

    @Override
    public void onRender(Canvas canvas)
    {
        canvas.drawBitmap(scaledBMP, 0 + bmp.getWidth() * 0.15f , 2.0f, null);

    }


}
