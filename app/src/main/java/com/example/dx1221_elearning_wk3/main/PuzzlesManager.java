package com.example.dx1221_elearning_wk3.main;

import android.graphics.Canvas;
import android.util.Log;

import com.example.dx1221_elearning_wk3.mgp2d.core.GameEntity;

import java.util.Random;

public class PuzzlesManager extends GameEntity
{

    Puzzle activePuzzle;

    private static ColorPuzzle colorPuzzle;
    private static MathPuzzle mathPuzzle;
    private static WordPuzzle wordPuzzle;

    private boolean isPlayingPuzzle;
    private static PuzzlesManager instance = null;

    float PuzzleTimer ;

    enum PuzzleType
    {
        COLOR,

        MATH,
        WORD,

    }


    private PuzzlesManager()
    {
        isPlayingPuzzle = false;
        PuzzleTimer = 5f;

        colorPuzzle = new ColorPuzzle();
        mathPuzzle = new MathPuzzle();
        wordPuzzle = new WordPuzzle();
    }

    @Override
    public void onUpdate(float dt)
    {

        if(activePuzzle != null)
        {
            PuzzleTimer -= dt;
            activePuzzle.PlayPuzzle(dt);

            if(PuzzleTimer <= 0)
            {
                PlayerEntity.getInstance().TakeDamage();
                EndPuzzle();
            }

        }

    }

    public void StartPuzzle()
    {
        int RandomPuzzle = (int)(Math.random() * PuzzleType.values().length);
        PuzzleTimer = 5f;
        switch(PuzzleType.values()[RandomPuzzle])
        {
            case COLOR:
                activePuzzle = colorPuzzle;
                break;

            case MATH:
                activePuzzle = mathPuzzle;
                break;

            case WORD:
                activePuzzle = wordPuzzle;
                break;

        }
        activePuzzle.RandomizePuzzle();
        isPlayingPuzzle = true;
    }

    public void Clear()
    {
        isPlayingPuzzle = false;
        PuzzleTimer = 5f;
        activePuzzle = null;

    }

    public void EndPuzzle()
    {
        activePuzzle = null;
        isPlayingPuzzle = false;
    }

    public boolean playingPuzzle()
    {
        return isPlayingPuzzle;
    }

    public static synchronized PuzzlesManager getInstance()
    {
        if(instance == null)
            instance = new PuzzlesManager();

        return instance;
    }

    @Override
    public void onRender(Canvas canvas) {
        if(activePuzzle != null)
        {
            activePuzzle.onRender(canvas);
        }
    }
}
