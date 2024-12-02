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

    private float gravity;

    private float moveSpeed;

    private float fallSpeed;

    private float flySpeed;

    private int gameHeight;
    private int gameWidth;

    Bitmap sprite;
    Bitmap bmp;

    boolean flying;

    public int Health;

    public PlayerEntity()
    {

        moveSpeed = 30f;
        fallSpeed = 20f;
        gravity = 9.81f;
        flySpeed = 25f;

        flying = false;

        gameWidth = GameActivity.instance.getResources().getDisplayMetrics().widthPixels;
        gameHeight = GameActivity.instance.getResources().getDisplayMetrics().heightPixels;

        _position.x  = gameWidth / 2f;
        _position.y = gameHeight / 2f;

        bmp = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.cat_player);
        sprite = Bitmap.createScaledBitmap(bmp, (int) (bmp.getWidth() * 1.5f), (int) (bmp.getHeight() * 1.5f), true);

        //FPS for sprites can be in 10, 12, 24, 30
        _animatedSprite = new AnimatedSprite(sprite, 1, 10, 30);


        _size = new Vector2(bmp.getWidth()/10, bmp.getHeight());
    }

    public void TakeDamage()
    {
        this.Health--;
    }

    @Override
    public void onUpdate(float dt)
    {

        super.onUpdate(dt);
        _animatedSprite.update(dt);
        Log.d("Sprite Width", " " + sprite.getWidth());
        HandleGravity(dt);

    }

    void HandleDeath()
    {
        if(Health <= 0)
        {
            //Die
            Die();
        }
    }

    void Die()
    {

    }

    public void FlipLeft()
    {
        sprite = Bitmap.createScaledBitmap(bmp, (int) -(bmp.getWidth() * 1.5f), (int) (bmp.getHeight() * 1.5f), true);
        _animatedSprite.SetBitMap(sprite);
    }

    public void FlipRight()
    {
        sprite = Bitmap.createScaledBitmap(bmp, (int) (bmp.getWidth() * 1.5f), (int) (bmp.getHeight() * 1.5f), true);
        _animatedSprite.SetBitMap(sprite);
    }


    public void MoveLeft(double dt)
    {
        if(_position.x >= 0 + _size.x)
        {
            _position.x -= (float)dt * moveSpeed * 20f;
        }
    }

    public void MoveRight(double dt)
    {
        if(_position.x <= gameWidth - _size.x)
        {
            _position.x += (float)dt * moveSpeed * 20f;
        }
    }

    private void HandleGravity(double dt)
    {
        if(_position.y <= gameHeight - _size.y && !flying)
        {
            _position.y += gravity * (float)dt * fallSpeed;
        }
    }

    public void Fly(double dt)
    {
        flying = true;
        if(_position.y >= 0 + _size.y)
        {

            _position.y -= flySpeed * (float)dt * 20;
        }
    }

    public void EndFly()
    {
        flying = false;

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
