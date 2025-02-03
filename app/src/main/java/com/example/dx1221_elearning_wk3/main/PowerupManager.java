package com.example.dx1221_elearning_wk3.main;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.provider.CalendarContract;

import com.example.dx1221_elearning_wk3.mgp2d.core.GameEntity;

import java.util.ArrayList;

public class PowerupManager extends GameEntity
{
    ObjectPool<Shield> shieldObjectPool;

    public static PowerupManager instance;

    public ArrayList<Shield> shields;

    private Paint numTxt;

    public PowerupManager()
    {

        shields = new ArrayList<>();
        shieldObjectPool = new ObjectPool<>(() -> new Shield(), 3);
        numTxt = new Paint();

        numTxt.setTextSize(100);
        numTxt.setTextAlign(Paint.Align.CENTER);
        numTxt.setColor(Color.BLACK);

    }


    public static synchronized PowerupManager getInstance()
    {
        if(instance == null)
            instance = new PowerupManager();

        return instance;
    }

    public void addShield(int amount)
    {
        for(int i = 0; i < amount; i++)
        {
            Shield temp = shieldObjectPool.acquire();
            shields.add(temp);
        }
    }


    public void UseShield()
    {
        if(HasShield())
        {
            shieldObjectPool.release(shields.get(shields.size() - 1));
            shields.remove(shields.get(shields.size() - 1));
        }

    }

    public boolean HasShield()
    {
        return (!shields.isEmpty());
    }

    @Override
    public void onRender(Canvas canvas)
    {
        for(int i = 0; i < shields.size();i++)
        {
            shields.get(i).onRender(canvas);
        }
        if(getInstance().HasShield())
        {
            canvas.drawText("x" + shields.size(), 450 , 100, numTxt);
        }


    }

}
