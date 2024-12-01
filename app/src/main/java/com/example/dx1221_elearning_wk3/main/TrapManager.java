package com.example.dx1221_elearning_wk3.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.lights.Light;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dx1221_elearning_wk3.R;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameActivity;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameEntity;
import com.example.dx1221_elearning_wk3.mgp2d.core.Vector2;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
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

    ArrayList<Traps> ActiveTraps;
    private static TrapManager instance = null;

    Bitmap ArrowAsset;
    Bitmap LightningAsset;
    Bitmap CoconutAsset;

    int GameWidth;
    int GameHeight;

    private TrapManager()
    {
        ActiveTraps = new ArrayList<>();

        //TODO: Init trap assets (Animation if have)
        Bitmap ArrowBmp = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.arrow);
        Bitmap CoconutBmp = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.coconut1);
        LightningAsset = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.coconut1);

        CoconutAsset = Bitmap.createScaledBitmap(CoconutBmp, 100, 100 ,true);
        ArrowAsset = Bitmap.createScaledBitmap(ArrowBmp, ArrowBmp.getWidth() * 2, ArrowBmp.getHeight() * 2 ,true);

        GameHeight = GameActivity.instance.getResources().getDisplayMetrics().heightPixels;
        GameWidth = GameActivity.instance.getResources().getDisplayMetrics().widthPixels;
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
            Log.d("Active Traps:", "" + i + ActiveTraps.get(i));
            ActiveTraps.get(i).DoEffect(dt);
        }
    }

    public void SpawnTrap(PlayerEntity player)
    {
        int RandomTrap = (int)(Math.random() * TrapType.values().length);
        Log.d("Spawn Trap", "" + TrapType.values()[RandomTrap]);

        switch(TrapType.values()[RandomTrap])
        {
            case ARROW:
                Traps ArrowTrap = new ArrowTrap(ArrowAsset);
                ArrowTrap.Spawn(new Vector2(GameWidth, player.getPosition().y));

                ActiveTraps.add(ArrowTrap);
                break;

            case COCONUT:
                Traps CoconutTrap = new CoconutTrap(CoconutAsset);
                CoconutTrap.Spawn(new Vector2(player.getPosition().x, 0f - CoconutAsset.getHeight()));

                ActiveTraps.add(CoconutTrap);

                break;

            case LIGHTNING:
                Traps LightningTrap = new LightningTrap(LightningAsset);
                LightningTrap.Spawn(new Vector2((float)GameWidth, (float)(Math.random() * GameHeight)));

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
