package com.example.dx1221_elearning_wk3.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.dx1221_elearning_wk3.mgp2d.extra.AnimatedSprite;

public class LightningTrap extends Traps {

    private AnimatedSprite lightningSprite;
    private int lightningFrameWidth;
    private int lightningFrameHeight;

    public LightningTrap(Bitmap lightningAsset) {
        super(lightningAsset);

        Bitmap scaledLightningBmp = Bitmap.createScaledBitmap(
                lightningAsset,
                (int) (lightningAsset.getWidth() * 1.5),
                (int) (lightningAsset.getHeight() * 1.5),
                true
        );

        lightningSprite = new AnimatedSprite(scaledLightningBmp, 1, 8, 30);
        lightningFrameHeight = scaledLightningBmp.getHeight();
        lightningFrameWidth = scaledLightningBmp.getWidth() / 8;
    }

    @Override
    public void DoEffect(double dt) {
        _position.x -= MainGameScene.WorldSpeed * dt;

        // Update the animation
        lightningSprite.update((float) dt);
    }

    @Override
    public void onRender(Canvas canvas) {
        // Render the animated lightning sprite
        lightningSprite.render(canvas,
                (int) (_position.x - lightningFrameWidth / 2),
                (int) (_position.y - lightningFrameHeight / 2),
                null);
    }
}
