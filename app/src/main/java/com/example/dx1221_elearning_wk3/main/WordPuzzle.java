package com.example.dx1221_elearning_wk3.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.dx1221_elearning_wk3.R;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameActivity;

public class WordPuzzle extends Puzzle
{

    public WordPuzzle()
    {
        Bitmap Backgroundbmp = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.word_puzzle);
        Background = Bitmap.createScaledBitmap(Backgroundbmp, (int)(MainGameScene.screenWidth * 0.8f), (int)(MainGameScene.screenHeight * 0.8f), true   );
    }

    @Override
    public void PlayPuzzle(double dt) {

    }
}
