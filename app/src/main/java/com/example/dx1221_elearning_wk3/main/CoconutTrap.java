package com.example.dx1221_elearning_wk3.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.dx1221_elearning_wk3.R;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameActivity;

public class CoconutTrap extends Traps{
    private float TimerBeforeDrop;
    private float projectileSpeed;

    private boolean showIndicator;
    private Bitmap IndicatorAsset;

    public CoconutTrap(Bitmap trapAsset) {
        super(trapAsset);
        projectileSpeed = 1000f;
        TimerBeforeDrop = 1f;
        showIndicator = true;
        Bitmap indicatorBmp = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.warning_sign);
        IndicatorAsset = Bitmap.createScaledBitmap(indicatorBmp, indicatorBmp.getWidth(), indicatorBmp.getHeight(), true);

    }

    @Override
    public void DoEffect(double dt)
    {
        TimerBeforeDrop -= (float)dt;
        if(TimerBeforeDrop <= 0)
        {
            showIndicator = false;

            _position.y += projectileSpeed * (float)dt;
        }


    }

    @Override
    public void onRender(Canvas canvas) {
        super.onRender(canvas);
        if(showIndicator)
        {
            canvas.drawBitmap(IndicatorAsset, _position.x , _position.y + IndicatorAsset.getWidth() , null);
        }
    }
}
