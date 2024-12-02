package com.example.dx1221_elearning_wk3.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.dx1221_elearning_wk3.mgp2d.core.Vector2;
import com.example.dx1221_elearning_wk3.mgp2d.extra.AnimatedSprite;

public class LightningTrap extends Traps {

    private AnimatedSprite lightningSprite;
    private int lightningFrameWidth;
    private int lightningFrameHeight;

    private boolean didCollision = false;

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
        _size = new Vector2(scaledLightningBmp.getWidth() / 8f, scaledLightningBmp.getHeight() * 0.3f);
    }

    @Override
    public void DoCollision(PlayerEntity player) {
        if(didCollision)
            return;
        super.DoCollision(player);
        didCollision = true;
    }

    @Override
    public void DoEffect(double dt) {
        _position.x -= MainGameScene.WorldSpeed * (float)dt;

        // Update the animation
        lightningSprite.update((float) dt);

        if(_position.x <= 0)
        {
            TrapManager.getInstance().DisableTrap(this);
        }
    }

    @Override
    public void onRender(Canvas canvas) {
        // Render the animated lightning sprite
        lightningSprite.render(canvas,
                (int) (_position.x - lightningFrameWidth / 2f),
                (int) (_position.y - lightningFrameHeight / 2f),
                null);
    }
}
