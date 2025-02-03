package com.example.dx1221_elearning_wk3.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.dx1221_elearning_wk3.R;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameActivity;
import com.example.dx1221_elearning_wk3.mgp2d.extra.AnimatedSprite;

public class CoconutTrap extends Traps{
    private float TimerBeforeDrop;
    private float projectileSpeed;

    private boolean showIndicator;

    private AnimatedSprite indicatorSprite;
    private int indicatorHeight;
    private int indicatorWidth;
    private float indicatorOffsetY = 50f;

    private static Bitmap originalIndicatorBmp;
    private static Bitmap scaledIndicatorBmp;

    public CoconutTrap(Bitmap trapAsset) {
        super(trapAsset);
        projectileSpeed = 1000f;
        TimerBeforeDrop = 1f;
        showIndicator = true;

        if(originalIndicatorBmp == null && scaledIndicatorBmp == null)
        {
            originalIndicatorBmp = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.alert_ps);
            scaledIndicatorBmp = Bitmap.createScaledBitmap(
                    originalIndicatorBmp,
                    (int) (originalIndicatorBmp.getWidth() * 1.5),
                    (int) (originalIndicatorBmp.getHeight() * 1.5),
                    true
            );
        }
        indicatorSprite = new AnimatedSprite(scaledIndicatorBmp, 1, 9, 30);

        indicatorHeight = scaledIndicatorBmp.getHeight();
        indicatorWidth = scaledIndicatorBmp.getWidth() / 9;

    }

    @Override
    public void DoEffect(double dt)
    {
        TimerBeforeDrop -= (float)dt;
        if(TimerBeforeDrop <= 0)
        {
            showIndicator = false;

            _position.y += projectileSpeed * MainGameScene.speedMultipler * (float)dt;
        }
        if (showIndicator)
        {
            indicatorSprite.update((float)dt);
        }

        if(_position.y >= MainGameScene.screenHeight)
        {
            TrapManager.getInstance().DisableTrap(this);
        }
    }

    @Override
    public void reset()
    {
        super.reset();
        showIndicator = true;
        TimerBeforeDrop = 2;

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
            indicatorSprite.render(canvas,
                    (int) (_position.x - indicatorWidth / 2),
                    (int) (_position.y + indicatorHeight + indicatorOffsetY),
                    null);        }
    }
}
