package com.example.dx1221_elearning_wk3.main;

import android.graphics.Canvas;
import android.util.Log;

import com.example.dx1221_elearning_wk3.mgp2d.core.GameEntity;

public class PuzzlesManager extends GameEntity
{

    Puzzle activePuzzle;

    private boolean isPlayingPuzzle;
    private static PuzzlesManager instance = null;

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
    }
    @Override
    public void onUpdate(float dt)
    {
        if(activePuzzle != null)
        {
            activePuzzle.PlayPuzzle(dt);
        }

    }

    public void StartPuzzle()
    {
        int RandomPuzzle = (int)(Math.random() * PuzzleType.values().length);
        Log.d("Spawn Trap", "" + PuzzleType.values()[RandomPuzzle]);


        switch(PuzzleType.values()[RandomPuzzle])
        {
            case COLOR:
                break;

            case MATH:
                break;

            case MEMORY:
                break;

            case WORD:
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

    }
}
