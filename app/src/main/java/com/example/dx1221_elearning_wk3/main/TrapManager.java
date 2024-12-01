package com.example.dx1221_elearning_wk3.main;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.dx1221_elearning_wk3.mgp2d.core.GameEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.inject.Singleton;

public class TrapManager extends GameEntity
{


    enum TrapType
    {
        LIGHTNING,

        ARROW,

        COCONUT,

    }

    List<Traps> ActiveTraps;

    private static TrapManager instance = null;

    Bitmap ArrowAsset;
    Bitmap LightningAsset;

    Bitmap CoconutAsset;

    private TrapManager()
    {
        //TODO: Init trap assets (Animation if have)
    }
    public static synchronized TrapManager getInstance()
    {
        if(instance == null)
            instance = new TrapManager();

        return instance;
    }

    @Override
    public void onUpdate(float dt)
    {
        for(int i = 0; i < ActiveTraps.size();i++)
        {
            ActiveTraps.get(i).DoEffect(dt);
        }
    }

    private void SpawnTrap()
    {
        int RandomTrap = (int)(Math.random() * TrapType.values().length);
        switch(TrapType.values()[RandomTrap])
        {
            case ARROW:
                Traps ArrowTrap = new ArrowTrap(ArrowAsset);
                ActiveTraps.add(ArrowTrap);
                break;

            case COCONUT:
                Traps CoconutTrap = new CoconutTrap(ArrowAsset);
                ActiveTraps.add(CoconutTrap);

                break;

            case LIGHTNING:
                Traps LightningTrap = new LightningTrap(ArrowAsset);
                ActiveTraps.add(LightningTrap);

                break;
        }

    }

    public void HandleCollision(PlayerEntity player)
    {
        for(int i = 0; i < ActiveTraps.size();i++)
        {
            ActiveTraps.get(i).HandleCollision(player);
        }
    }


    @Override
    public void onRender(Canvas canvas)
    {
        for(int i = 0; i < ActiveTraps.size(); i++)
        {
            ActiveTraps.get(i).onRender(canvas);
        }
    }
}
