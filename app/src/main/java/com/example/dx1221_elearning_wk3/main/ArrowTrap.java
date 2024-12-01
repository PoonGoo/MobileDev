package com.example.dx1221_elearning_wk3.main;

import android.graphics.Bitmap;

public class ArrowTrap extends Traps
{
    private float TimerBeforeShoot;
    private float projectileSpeed;

    public ArrowTrap(Bitmap trapAsset) {
        super(trapAsset);
        projectileSpeed = 10f;
    }

    @Override
    public void DoEffect(double dt)
    {
        TimerBeforeShoot -= (float)dt;
        if(TimerBeforeShoot <= 0)
        {
            _position.x -= projectileSpeed * dt;
        }


    }
}
