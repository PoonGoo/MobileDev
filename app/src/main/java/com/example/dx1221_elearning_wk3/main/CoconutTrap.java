package com.example.dx1221_elearning_wk3.main;

import android.graphics.Bitmap;

public class CoconutTrap extends Traps{
    private float TimerBeforeDrop;
    private float projectileSpeed;

    public CoconutTrap(Bitmap trapAsset) {
        super(trapAsset);
        projectileSpeed = 10f;
    }

    @Override
    public void DoEffect(double dt)
    {
        TimerBeforeDrop -= (float)dt;
        if(TimerBeforeDrop <= 0)
        {
            _position.y += projectileSpeed * (float)dt;
        }


    }
}
