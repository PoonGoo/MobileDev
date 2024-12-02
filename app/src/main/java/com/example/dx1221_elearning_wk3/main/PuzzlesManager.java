package com.example.dx1221_elearning_wk3.main;

import android.graphics.Canvas;
import android.util.Log;

import com.example.dx1221_elearning_wk3.mgp2d.core.GameEntity;

import java.util.Random;

public class PuzzlesManager extends GameEntity
{

    Puzzle activePuzzle;

    private boolean isPlayingPuzzle;
    private static PuzzlesManager instance = null;

    float PuzzleTimer ;

    enum PuzzleType
    {
        COLOR,

        MATH,

        MEMORY,

        WORD,

    }


    private PuzzlesManager()
    {
        isPlayingPuzzle = false;
        PuzzleTimer = 5f;
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
                EndPuzzle(activePuzzle);
            }

        }

    }

    public void StartPuzzle()
    {
        int RandomPuzzle = (int)(Math.random() * PuzzleType.values().length);
        Log.d("Spawn Trap", "" + PuzzleType.values()[RandomPuzzle]);
        RandomPuzzle = 3;
        PuzzleTimer = 5f;

        switch(PuzzleType.values()[RandomPuzzle])
        {
            case COLOR:
                Puzzle ColorPuzzle = new ColorPuzzle();
                activePuzzle = ColorPuzzle;
                break;

            case MATH:
                Puzzle MathPuzzle = new MathPuzzle();
                activePuzzle = MathPuzzle;
                break;

            case MEMORY:
                Puzzle MemoryPuzzle = new MemoryPuzzle();
                activePuzzle = MemoryPuzzle;
                break;

            case WORD:
                Puzzle WordPuzzle = new WordPuzzle();
                activePuzzle = WordPuzzle;
                break;

        }
        isPlayingPuzzle = true;
    }

    public void EndPuzzle(Puzzle puzzle)
    {
        puzzle = null;
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
