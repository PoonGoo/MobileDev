package com.example.dx1221_elearning_wk3.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.util.Log;

import com.example.dx1221_elearning_wk3.R;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameActivity;
import com.example.dx1221_elearning_wk3.mgp2d.core.Vector2;
import com.google.android.gms.games.PlayGames;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class WordPuzzle extends Puzzle
{

    String[] wordList = {"QUIZ", "JINX", "VAMP", "HAWK", "WHIP", "CROW", "ZEST", "BOLD", "GRIN"};

    private static ArrayList<Character> characters;
    private static ArrayList<LetterButton> letterButtons;

    String WordToMake;

    private static ArrayList<Character> userCharacters;

    float circleOffset = -500f;

    Paint wordPaint;

    private static Bitmap letterScaled;
    private static Bitmap letterHolder;

    private SoundPool soundPool;
    private int correctSoundId;
    private int wrongSoundId;

    private boolean clicked = false;


    public WordPuzzle()
    {
        wordPaint = new Paint();
        wordPaint.setTextSize(100);
        wordPaint.setTextAlign(Paint.Align.LEFT);
        wordPaint.setColor(Color.WHITE);

        if(characters == null)
        {
            characters = new ArrayList<>();
        }
        if(letterButtons == null)
        {
            letterButtons = new ArrayList<>();
        }
        if(userCharacters == null)
        {
            userCharacters = new ArrayList<>();
        }

        Bitmap Backgroundbmp = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.word_puzzle);
        Background = Bitmap.createScaledBitmap(Backgroundbmp, (int)(MainGameScene.screenWidth * 1f), (int)(MainGameScene.screenHeight * 1f), true   );


        letterHolder = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.word_container);
        letterScaled = Bitmap.createScaledBitmap(letterHolder, (int) (letterHolder.getWidth() * 3f), (int) (letterHolder.getHeight() * 3f), true);

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(2)
                .setAudioAttributes(audioAttributes)
                .build();

        correctSoundId = soundPool.load(GameActivity.instance, R.raw.correct_sound, 1);
        wrongSoundId = soundPool.load(GameActivity.instance, R.raw.wrong_sound, 1);

    }

    @Override
    public void PlayPuzzle(double dt)
    {
        int soundVolume = SharedPrefManager.getInstance().readFromSharedPreferences(GameActivity.instance, "settings", "sound_volume");
        float volume = soundVolume / 100f;

        for(int i = 0; i < letterButtons.size();i++)
        {
            if(letterButtons.get(i).isClicked() && !userCharacters.contains(letterButtons.get(i).letter))
            {
                userCharacters.add(letterButtons.get(i).letter);
                clicked = true;
            }
        }

        if(!TouchHandler.getInstance().Pressed())
        {
            if (convertToString(userCharacters).equals(WordToMake))
            {

                soundPool.play(correctSoundId, volume, volume, 1, 0, 1);
                PlayerEntity.getInstance().Heal();

                completed = true;

                PuzzlesManager.getInstance().EndPuzzle();
            }
            else
            {
                if(clicked)
                    soundPool.play(wrongSoundId, volume, volume, 1, 0, 1);

                userCharacters.clear();
            }
        }
        clicked = false;
        Log.d("User Characters", " " + convertToString(userCharacters));

    }

    @Override
    public void RandomizePuzzle()
    {
        clicked = false;
        letterButtons.clear();
        characters.clear();
        userCharacters.clear();

        int RandomWordindex = (int)(Math.random() * wordList.length);

        WordToMake = wordList[RandomWordindex];

        char[] CharArr = WordToMake.toCharArray();

        for(int i = 0; i < CharArr.length; i++)
        {
            characters.add(CharArr[i]);
        }

        Collections.shuffle(characters);

        for(int i = 0; i < characters.size(); i++)
        {
            letterButtons.add(new LetterButton(letterScaled, characters.get(i)));
        }

        letterButtons.get(0).Spawn(new Vector2(circleOffset + MainGameScene.screenWidth / 2f - letterScaled.getWidth() , MainGameScene.screenHeight / 2f));
        letterButtons.get(1).Spawn(new Vector2(circleOffset + MainGameScene.screenWidth / 2f + letterScaled.getWidth() , MainGameScene.screenHeight / 2f));
        letterButtons.get(2).Spawn(new Vector2(circleOffset + MainGameScene.screenWidth / 2f, MainGameScene.screenHeight / 2f - letterScaled.getHeight() ));
        letterButtons.get(3).Spawn(new Vector2(circleOffset + MainGameScene.screenWidth / 2f, MainGameScene.screenHeight / 2f + letterScaled.getHeight()));
    }

    private String convertToString(ArrayList<Character> charList) {
        StringBuilder sb = new StringBuilder();
        for (Character c : charList)
        {
            sb.append(c); // Append each character to the StringBuilder
        }
        return sb.toString();
    }



    @Override
    public void onRender(Canvas canvas) {
        canvas.drawBitmap(Background, 0  , 0, null);

        for(int i = 0; i < letterButtons.size();i++)
        {
            letterButtons.get(i).onRender(canvas);
        }

        canvas.drawText(convertToString(userCharacters), MainGameScene.screenWidth * 0.66f, MainGameScene.screenHeight / 2f, wordPaint);

    }
}
