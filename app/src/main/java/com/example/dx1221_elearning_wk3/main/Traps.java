package com.example.dx1221_elearning_wk3.main;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.dx1221_elearning_wk3.mgp2d.core.GameEntity;
import com.example.dx1221_elearning_wk3.mgp2d.core.Vector2;

public abstract class Traps extends GameEntity {

    public Traps(Bitmap trapAsset, Vector2 SpawnPos)
    {

    }

    @Override
    public void onRender(Canvas canvas) {

    }

    public abstract void DoEffect();
}
