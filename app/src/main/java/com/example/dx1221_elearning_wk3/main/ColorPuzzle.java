package com.example.dx1221_elearning_wk3.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.widget.Button;

import androidx.core.content.ContextCompat;

import com.example.dx1221_elearning_wk3.R;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameActivity;
import com.example.dx1221_elearning_wk3.mgp2d.core.Vector2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import android.media.SoundPool;
import android.media.AudioAttributes;

public class ColorPuzzle extends Puzzle
{
    SoundPool soundPool;
    int correctSoundId;
    int wrongSoundId;

    private static Bitmap blue;
    private static Bitmap green;
    private static Bitmap grey;
    private static Bitmap purple;
    private static Bitmap red;
    private static Bitmap yellow;

    private static Bitmap Backgroundbmp;
    private static ArrayList<ColorButton> colorButtons;

    int blueColor;
    int greenColor;
    int greyColor;
    int purpleColor;
    int redColor;
    int yellowColor;

    ColorButton blueButton;
    ColorButton greenButton;
    ColorButton greyButton;
    ColorButton purpleButton;
    ColorButton redButton;
    ColorButton yellowButton;

    ArrayList<ColorButton> currentButtonsShown;
    ColorButton correctButton;

    Paint QuestionTxt;

    private static Dictionary<ColorButton, Integer> ButtonToColor;
    public ColorPuzzle()
    {
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(2)
                .setAudioAttributes(audioAttributes)
                .build();

        // Load sound files
        correctSoundId = soundPool.load(GameActivity.instance, R.raw.correct_sound, 1);
        wrongSoundId = soundPool.load(GameActivity.instance, R.raw.wrong_sound, 1);

        QuestionTxt = new Paint();

        QuestionTxt.setTextSize(150);
        QuestionTxt.setTextAlign(Paint.Align.CENTER);


        currentButtonsShown = new ArrayList<>();
        if(Backgroundbmp == null)
        {
            Backgroundbmp = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.color_puzzle);
        }

        Background = Bitmap.createScaledBitmap(Backgroundbmp, (int)(MainGameScene.screenWidth * 0.8f), (int)(MainGameScene.screenHeight * 0.8f), true   );

        blueColor = Color.BLUE;
        greenColor = Color.GREEN;
        greyColor = Color.GRAY; // Use Color.GRAY for grey
        purpleColor = Color.rgb(128, 0, 128); // Purple is not predefined, use RGB values
        redColor = Color.RED;
        yellowColor = Color.YELLOW;

        if(blue == null)
        {
            blue = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.element_blue_diamond);
        }
        if(green == null)
        {
            green = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.element_green_diamond);
        }
        if(grey == null)
        {
            grey = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.element_grey_diamond);
        }
        if(purple == null)
        {
            purple = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.element_purple_diamond);
        }
        if(red == null)
        {
            red = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.element_red_diamond);
        }
        if(yellow == null)
        {
            yellow = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.element_yellow_diamond);
        }

        blueButton = new ColorButton(blue);
        greenButton = new ColorButton(green);
        greyButton = new ColorButton(grey);
        purpleButton = new ColorButton(purple);
        redButton = new ColorButton(red);
        yellowButton = new ColorButton(yellow);

        if(ButtonToColor == null)
        {
            ButtonToColor = new Hashtable<>();
            ButtonToColor.put(blueButton, blueColor);
            ButtonToColor.put(greenButton, greenColor);
            ButtonToColor.put(greyButton, greyColor);
            ButtonToColor.put(purpleButton, purpleColor );
            ButtonToColor.put(redButton, redColor);
            ButtonToColor.put(yellowButton, yellowColor);
        }

        if(colorButtons == null)
        {
            colorButtons = new ArrayList<>();

            colorButtons.add(blueButton);
            colorButtons.add(greenButton);
            colorButtons.add(greyButton);
            colorButtons.add(purpleButton);
            colorButtons.add(redButton);
            colorButtons.add(yellowButton);
        }




    }

    @Override
    public void PlayPuzzle(double dt)
    {
        Log.d("Update Color Puzzle", "Updating Color Puzzle");
        //Random generate correct answer
        currentButtonsShown.get(0).Spawn(new Vector2(Background.getWidth() * 0.17f + Background.getWidth() * 0.2f, Background.getHeight() * 0.5f));
        currentButtonsShown.get(1).Spawn(new Vector2(Background.getWidth() * 0.47f + Background.getWidth() * 0.2f, Background.getHeight() * 0.5f));
        currentButtonsShown.get(2).Spawn(new Vector2(Background.getWidth() * 0.77f + Background.getWidth() * 0.2f, Background.getHeight() * 0.5f));

        int ColorOfText = ButtonToColor.get(correctButton);
        QuestionTxt.setColor(ColorOfText);

        for(int i = 0; i < currentButtonsShown.size(); i++)
        {
            if(currentButtonsShown.get(i).isClicked())
            {
                if(currentButtonsShown.get(i) == correctButton)
                {
                    Log.d("ButtonPressed", "Correct");
                    soundPool.play(correctSoundId, 1, 1, 1, 0, 1);
                    PlayerEntity.getInstance().Heal();
                    PuzzlesManager.getInstance().EndPuzzle();

                }
                else
                {
                    Log.d("ButtonPressed", "Wrong");
                    soundPool.play(wrongSoundId, 1, 1, 1, 0, 1);
                    PlayerEntity.getInstance().TakeDamage();
                    PuzzlesManager.getInstance().EndPuzzle();
                }
            }
        }


    }

    @Override
    public void RandomizePuzzle()
    {
        currentButtonsShown.clear();

        correctButton = colorButtons.get((int)(Math.random() * colorButtons.size()));


        currentButtonsShown.add(correctButton);

        for(int i = 0 ; i < 2 ; i++)
        {
            ColorButton tempButton = colorButtons.get((int)(Math.random() * colorButtons.size()));
            Log.d("Temp Button", " " + tempButton);
            if(currentButtonsShown.contains(tempButton))
            {
                i--;
                continue;
            }
            currentButtonsShown.add(tempButton);

        }

        Collections.shuffle(currentButtonsShown);
    }

    @Override
    public void onRender(Canvas canvas)
    {
        super.onRender(canvas);
        for(int i = 0; i < currentButtonsShown.size();i++)
        {
            currentButtonsShown.get(i).onRender(canvas);
        }

        canvas.drawText("Choose this color",0 + MainGameScene.screenWidth / 2f + Background.getWidth() * 0.075f, Background.getHeight() * 0.3f,QuestionTxt);
    }

    @Override
    protected void finalize() throws Throwable
    {
        super.finalize();
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
    }
}
