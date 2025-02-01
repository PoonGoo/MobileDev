package com.example.dx1221_elearning_wk3.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.dx1221_elearning_wk3.R;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameActivity;
import com.example.dx1221_elearning_wk3.mgp2d.core.Vector2;

import com.example.dx1221_elearning_wk3.mgp2d.extra.AnimatedSprite;

public class ArrowTrap extends Traps
{
    private float TimerBeforeShoot;
    private float projectileSpeed;

    private boolean showIndicator;

    private AnimatedSprite indicatorSprite;
    private int indicatorHeight;
    private int indicatorWidth;
    private float indicatorOffsetX = 0f;
    private static Bitmap originalIndicatorBmp;
    private static Bitmap scaledIndicatorBmp;

    public ArrowTrap(Bitmap trapAsset) {
        super(trapAsset);
        projectileSpeed = 1000f;
        TimerBeforeShoot = 2f;
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
    public void reset() {
        super.reset();
        showIndicator = true;
    }

    @Override
    public void DoEffect(double dt)
    {
        TimerBeforeShoot -= (float)dt;
        if(TimerBeforeShoot <= 0)
        {
            _position.x -= projectileSpeed * MainGameScene.speedMultipler * (float)dt;
            showIndicator = false;
        }
        
        if (showIndicator) {
            indicatorSprite.update((float) dt);
        }

        if(_position.x <= 0)
        {
            TrapManager.getInstance().DisableTrap(this);
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
        if (showIndicator) {
            indicatorSprite.render(canvas,
                    (int) (_position.x + indicatorOffsetX - indicatorWidth / 2),
                    (int) (_position.y - indicatorHeight / 2),
                    null);
        }

    }
}
