package com.example.dx1221_elearning_wk3.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.example.dx1221_elearning_wk3.R;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameActivity;
import com.example.dx1221_elearning_wk3.mgp2d.core.Vector2;

import java.util.ArrayList;
import java.util.Collections;

public class MathPuzzle extends Puzzle
{

    public enum QuestionType
    {
        ADDITION,
        SUBTRACT,
    }
    private int FirstNumber;
    private int SecondNumber;

    private int Total;

    private int NumOptions;

    ArrayList<MathOptions> Options;

    MathOptions correctOption;
    Paint QuestionTxt;

    QuestionType questionType;
    public MathPuzzle()
    {
        QuestionTxt = new Paint();
        QuestionTxt.setTextSize(100);
        QuestionTxt.setTextAlign(Paint.Align.CENTER);
        QuestionTxt.setColor(Color.WHITE);

        Bitmap Backgroundbmp = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.equation_puzzle);
        Bitmap border = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.math_border);

        Bitmap borderBMP = Bitmap.createScaledBitmap(border, (int)(border.getWidth() ), (int)(border.getHeight() ), true   );
        Background = Bitmap.createScaledBitmap(Backgroundbmp, (int)(MainGameScene.screenWidth * 0.8f), (int)(MainGameScene.screenHeight * 0.8f), true   );
        NumOptions = 3;
        int QType = (int)(Math.random() * QuestionType.values().length);
        questionType = QuestionType.values()[QType];
        switch(QuestionType.values()[QType])
        {
            case ADDITION:
                FirstNumber = (int)(Math.random() * 41) + 10; //Random number from 10 to 50
                SecondNumber = (int)(Math.random() * 41) + 10;
                Total = FirstNumber + SecondNumber;

                break;
            case SUBTRACT:
                FirstNumber = (int)(Math.random() * 41) + 10; //Random number from 10 to 50
                SecondNumber = (int)(Math.random() * 41) + 10;
                if(FirstNumber <= SecondNumber)
                {
                    int tempNum = FirstNumber;
                    FirstNumber = SecondNumber;
                    SecondNumber = tempNum;
                }

                Total = FirstNumber - SecondNumber;

                break;

        }

        Log.d("Equations", "Firstnumber: " + FirstNumber + " Second Number: " + SecondNumber + "Total: " + Total);

        Options = new ArrayList<>();

        correctOption = new MathOptions(Total, borderBMP);
        Options.add(correctOption);
        boolean Added = false;
        for(int i = 0; i < NumOptions - 1; i++)
        {
            Added = false;
            int tempRandomToAdd = (int)(Math.random() * 20 - 10); //Random between -10 and 10
            MathOptions tempOption = new MathOptions(Total + tempRandomToAdd, borderBMP);
            for(int j = 0; j < Options.size(); j++)
            {
                if(Options.get(j).optionNumber == tempOption.optionNumber)
                {
                    i--;
                    Added = true;
                }
            }
            if(Added)
            {
                continue;
            }
            Options.add(tempOption);
        }

        Collections.shuffle(Options);

    }
    @Override
    public void PlayPuzzle(double dt)
    {
        Options.get(0).Spawn(new Vector2(Background.getWidth() * 0.15f + Background.getWidth() * 0.2f , Background.getHeight() * 0.6f));
        Options.get(1).Spawn(new Vector2(Background.getWidth() * 0.45f + Background.getWidth() * 0.2f, Background.getHeight() * 0.6f));
        Options.get(2).Spawn(new Vector2(Background.getWidth() * 0.75f + Background.getWidth() * 0.2f, Background.getHeight() * 0.6f));

        for(int i = 0; i < Options.size(); i++)
        {
            if(Options.get(i).isClicked())
            {
                if(Options.get(i) == correctOption)
                {
                    Log.d("ButtonPressed", "Correct");
                    PuzzlesManager.getInstance().EndPuzzle(this);

                }
                else
                {
                    Log.d("ButtonPressed", "Wrong");
                    PlayerEntity.getInstance().TakeDamage();
                    PuzzlesManager.getInstance().EndPuzzle(this);
                }
            }
        }

    }

    @Override
    public void onRender(Canvas canvas)
    {
        super.onRender(canvas);
        for(int i = 0; i < Options.size();i++)
        {
            Options.get(i).onRender(canvas);
        }
        switch(questionType)
        {
            case ADDITION:
                canvas.drawText(FirstNumber + " + " + SecondNumber, 0 + MainGameScene.screenWidth / 2f + Background.getWidth() * 0.075f, Background.getHeight() * 0.5f, QuestionTxt);
                break;
            case SUBTRACT:
                canvas.drawText(FirstNumber + " -" + SecondNumber, 0 + MainGameScene.screenWidth / 2f + Background.getWidth() * 0.075f, Background.getHeight() * 0.5f, QuestionTxt);
                break;
        }

    }
}
