package com.example.dx1221_elearning_wk3.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.dx1221_elearning_wk3.R;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameActivity;
import com.example.dx1221_elearning_wk3.mgp2d.core.Vector2;

public class ArrowTrap extends Traps
{
    private float TimerBeforeShoot;
    private float projectileSpeed;

    private boolean showIndicator;
    private Bitmap IndicatorAsset;
    public ArrowTrap(Bitmap trapAsset) {
        super(trapAsset);
        projectileSpeed = 1000f;
        TimerBeforeShoot = 2f;
        Bitmap indicatorBmp = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.warning_sign);
        IndicatorAsset = Bitmap.createScaledBitmap(indicatorBmp, indicatorBmp.getWidth(), indicatorBmp.getHeight(), true);
        showIndicator = true;
    }

    @Override
    public void DoEffect(double dt)
    {
        TimerBeforeShoot -= (float)dt;
        if(TimerBeforeShoot <= 0)
        {
            _position.x -= projectileSpeed * (float)dt;
            showIndicator = false;
        }

   }

    @Override
    public void DoCollision(PlayerEntity player) {
        super.DoCollision(player);
        TrapManager.getInstance().DisableTrap(this);
    }

    @Override
    public void onRender(Canvas canvas) {
        super.onRender(canvas);
        if(showIndicator)
        {
            canvas.drawBitmap(IndicatorAsset, _position.x - IndicatorAsset.getWidth(), _position.y, null);
        }

    }
}
