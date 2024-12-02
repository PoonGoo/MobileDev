package com.example.dx1221_elearning_wk3.main;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.dx1221_elearning_wk3.mgp2d.core.GameEntity;
import com.example.dx1221_elearning_wk3.mgp2d.core.Vector2;

public abstract class Traps extends GameEntity {

    Bitmap bmp;

    boolean isActive;
    public Traps(Bitmap trapAsset)
    {
        isActive = false;
        bmp = trapAsset;
        _size =  new Vector2(bmp.getWidth(), bmp.getHeight());

    }

    public void Spawn(Vector2 spawnPt)
    {
        isActive = true;
        _position = spawnPt;
    }

    public void DoCollision(PlayerEntity player)
    {
        player.TakeDamage();
            }

    @Override
    public void onRender(Canvas canvas) {
        if(!isActive)
            return;

        canvas.drawBitmap(bmp, _position.x, _position.y , null);

    }

    public abstract void DoEffect(double dt);
}
