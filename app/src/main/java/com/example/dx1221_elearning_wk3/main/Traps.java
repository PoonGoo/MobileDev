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
import android.media.AudioAttributes;
import android.media.SoundPool;
import com.example.dx1221_elearning_wk3.R;

public abstract class Traps extends GameEntity {

    Bitmap bmp;

    private SoundPool soundPool;
    private int takeDamageSoundId;

    public Traps(Bitmap trapAsset)
    {
        isActive = false;
        bmp = trapAsset;
        _size =  new Vector2(bmp.getWidth(), bmp.getHeight());

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(1)
                .setAudioAttributes(audioAttributes)
                .build();

        takeDamageSoundId = soundPool.load(GameActivity.instance, R.raw.take_dmg_sound, 1);

    }

    public void Spawn(Vector2 spawnPt)
    {
        isActive = true;
        _position = spawnPt;
    }

    @SuppressLint({"MissingPermission", "NewApi"})
    public void DoCollision(PlayerEntity player)
    {
        int soundVolume = SharedPrefManager.getInstance().readFromSharedPreferences(GameActivity.instance, "settings", "sound_volume");
        float volume = soundVolume / 100f;

        player.TakeDamage();
        soundPool.play(takeDamageSoundId, volume, volume, 1, 0, 1);

        Vibrator vibrator = (Vibrator) GameActivity.instance.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null && vibrator.hasVibrator())
        {
            vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
        }


    }

    public void reset() {
        isActive = false;
        _position = new Vector2(0, 0);
    }

    @Override
    public void onRender(Canvas canvas) {
        if (!isActive) return;
        canvas.drawBitmap(bmp, _position.x, _position.y, null);
    }

    public abstract void DoEffect(double dt);

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
    }
}
