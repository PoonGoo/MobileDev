package com.example.dx1221_elearning_wk3.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.dx1221_elearning_wk3.R;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameActivity;

import java.util.ArrayList;

public class ColorPuzzle extends Puzzle
{

    public ColorPuzzle()
    {
        Bitmap Backgroundbmp = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.color_puzzle);
        Background = Bitmap.createScaledBitmap(Backgroundbmp, (int)(MainGameScene.screenWidth * 0.8f), (int)(MainGameScene.screenHeight * 0.8f), true   );
    }

    @Override
    public void PlayPuzzle(double dt) {

    }
}
