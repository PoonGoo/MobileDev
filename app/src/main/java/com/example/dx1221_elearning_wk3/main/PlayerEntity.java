package com.example.dx1221_elearning_wk3.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.example.dx1221_elearning_wk3.R;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameActivity;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameEntity;
import com.example.dx1221_elearning_wk3.mgp2d.core.Vector2;
import com.example.dx1221_elearning_wk3.mgp2d.extra.AnimatedSprite;

public class PlayerEntity extends GameEntity {

    private final AnimatedSprite _animatedSprite;


    private float moveSpeed;
    public PlayerEntity()
    {
        moveSpeed = 10f;
        _position.x = (float) GameActivity.instance.getResources().getDisplayMetrics().widthPixels /2;
        _position.y = (float) GameActivity.instance.getResources().getDisplayMetrics().heightPixels /2;

        Bitmap bmp = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.player_heli_body);
        Bitmap sprite = Bitmap.createScaledBitmap(bmp, (int) (bmp.getWidth() * 1.5f), (int) (bmp.getHeight() * 1.5f), true);

        //FPS for sprites can be in 10, 12, 24, 30
        _animatedSprite = new AnimatedSprite(sprite, 1, 7, 24);

        _size = new Vector2(bmp.getWidth()/7, bmp.getHeight());
    }

    @Override
    public void onUpdate(float dt)
    {
        super.onUpdate(dt);
        _animatedSprite.update(dt);



    }



    public void MoveLeft(double dt)
    {
        _position.x -= (float)dt * moveSpeed * 20f;
    }

    public void MoveRight(double dt)
    {
        _position.x += (float)dt * moveSpeed * 20f;
    }

    @Override
    public void onRender(Canvas canvas)
    {

/*        _dstRect.left = (int) _position.x - _sprite.getWidth() / 7 / 2;
        _dstRect.top = (int) _position.y - _sprite.getHeight() / 2;
        _dstRect.right = (int) _position.x + _sprite.getWidth() / 7 / 2;
        _dstRect.bottom = (int) _position.y + _sprite.getHeight() / 2;
        canvas.drawBitmap(_sprite, _srcRect, _dstRect, null);*/

        _animatedSprite.render(canvas, (int) _position.x, (int) _position.y, null);
    }
}
