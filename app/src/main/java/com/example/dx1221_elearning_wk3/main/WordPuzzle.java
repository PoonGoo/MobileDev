package com.example.dx1221_elearning_wk3.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import com.example.dx1221_elearning_wk3.R;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class WordPuzzle extends Puzzle
{

    String[] wordList = {"Quiz", "Jinx", "Vamp", "Hawk", "Whip", "Crow", "Zest", "Bold", "Grin"};

    ArrayList<Character> characters;
    ArrayList<LetterButton> letterButtons;

    String WordToMake;

    ArrayList<Character> userCharacters;

    public WordPuzzle()
    {
        characters = new ArrayList<>();
        letterButtons = new ArrayList<>();
        userCharacters = new ArrayList<>();
        Bitmap Backgroundbmp = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.word_puzzle);
        Background = Bitmap.createScaledBitmap(Backgroundbmp, (int)(MainGameScene.screenWidth * 0.8f), (int)(MainGameScene.screenHeight * 0.8f), true   );

        int RandomWordindex = (int)(Math.random() * wordList.length);

        WordToMake = wordList[RandomWordindex];

        char[] CharArr = WordToMake.toCharArray();

        for(int i = 0; i < CharArr.length; i++)
        {
            characters.add(CharArr[i]);
        }

        Collections.shuffle(characters);

    }

    @Override
    public void PlayPuzzle(double dt)
    {

    }

    @Override
    public void onRender(Canvas canvas) {
        super.onRender(canvas);
        for(int i = 0; i < letterButtons.size();i++)
        {
            letterButtons.get(i).onRender(canvas);
        }

    }
}
