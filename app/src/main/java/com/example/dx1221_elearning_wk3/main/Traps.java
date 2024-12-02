package com.example.dx1221_elearning_wk3.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.dx1221_elearning_wk3.mgp2d.core.GameActivity;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameEntity;
import com.example.dx1221_elearning_wk3.mgp2d.core.Vector2;
import android.os.VibrationEffect;
import android.os.Vibrator;


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

    @SuppressLint({"MissingPermission", "NewApi"})
    public void DoCollision(PlayerEntity player)
    {
        player.TakeDamage();
        Vibrator vibrator = (Vibrator) GameActivity.instance.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null && vibrator.hasVibrator()) {
            // Vibrate for 200 milliseconds
            vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));

        }
    }

    @Override
    public void onRender(Canvas canvas) {
        if(!isActive)
            return;

        canvas.drawBitmap(bmp, _position.x, _position.y , null);

    }

    public abstract void DoEffect(double dt);
}
