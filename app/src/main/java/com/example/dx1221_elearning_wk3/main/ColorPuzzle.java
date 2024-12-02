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


public class ColorPuzzle extends Puzzle
{

    Bitmap blue;
    Bitmap green;
    Bitmap grey;
    Bitmap purple;
    Bitmap red;
    Bitmap yellow;
    ArrayList<ColorButton> colorButtons;

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

     Dictionary<ColorButton, Integer> ButtonToColor;
    public ColorPuzzle()
    {
        QuestionTxt = new Paint();

        QuestionTxt.setTextSize(150);
        QuestionTxt.setTextAlign(Paint.Align.CENTER);

        colorButtons = new ArrayList<>();

        currentButtonsShown = new ArrayList<>();
        Bitmap Backgroundbmp = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.color_puzzle);
        Background = Bitmap.createScaledBitmap(Backgroundbmp, (int)(MainGameScene.screenWidth * 0.8f), (int)(MainGameScene.screenHeight * 0.8f), true   );

        blueColor = Color.BLUE;
        greenColor = Color.GREEN;
        greyColor = Color.GRAY; // Use Color.GRAY for grey
        purpleColor = Color.rgb(128, 0, 128); // Purple is not predefined, use RGB values
        redColor = Color.RED;
        yellowColor = Color.YELLOW;

        blue = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.element_blue_diamond);

        green = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.element_green_diamond);

        grey = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.element_grey_diamond);

        purple = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.element_purple_diamond);

        red = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.element_red_diamond);

        yellow = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.element_yellow_diamond);


        blueButton = new ColorButton(blue);
        greenButton = new ColorButton(green);
        greyButton = new ColorButton(grey);
        purpleButton = new ColorButton(purple);
        redButton = new ColorButton(red);
        yellowButton = new ColorButton(yellow);

        ButtonToColor = new Hashtable<>();
        ButtonToColor.put(blueButton, blueColor);
        ButtonToColor.put(greenButton, greenColor);
        ButtonToColor.put(greyButton, greyColor);
        ButtonToColor.put(purpleButton, purpleColor );
        ButtonToColor.put(redButton, redColor);
        ButtonToColor.put(yellowButton, yellowColor);

        colorButtons.add(blueButton);
        colorButtons.add(greenButton);
        colorButtons.add(greyButton);
        colorButtons.add(purpleButton);
        colorButtons.add(redButton);
        colorButtons.add(yellowButton);

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

                }
                else
                {
                    Log.d("ButtonPressed", "Wrong");

                }
            }
        }


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
}
