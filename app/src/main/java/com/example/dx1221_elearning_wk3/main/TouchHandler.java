package com.example.dx1221_elearning_wk3.main;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import com.example.dx1221_elearning_wk3.mgp2d.core.GameActivity;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameEntity;
import com.example.dx1221_elearning_wk3.mgp2d.core.Vector2;

public class TouchHandler extends GameEntity {

    private int _currentPointerID;

    private int _secondPointerID;

    private boolean isPressed;
    private boolean isPressed2;

    Vector2 SecondTouchPos;

    int gameWidth;

    public TouchHandler()
    {
        _currentPointerID = -1;
        _secondPointerID = -1;
        _size = new Vector2(24, 24);
        SecondTouchPos = new Vector2(0,0);
        gameWidth = GameActivity.instance.getResources().getDisplayMetrics().widthPixels;

    }

    public boolean Pressed()
    {
        return isPressed;
    }

    public boolean SecondPressed()
    {
        return isPressed2;
    }

    @Override
    public void onUpdate(float dt) {
        HandleTouch();
    }

    @Override
        public void onRender(Canvas canvas) {

    }

    private void HandleTouch()
    {
        MotionEvent motionEvent = GameActivity.instance.getMotionEvent();
        if(motionEvent == null) return;

        int action = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        int pointerID = motionEvent.getPointerId(actionIndex);

        _position = new Vector2(0,0);
        SecondTouchPos = new Vector2(0,0);

        // Handle the first pointer (Primary touch)
        if (_currentPointerID == -1 && (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_DOWN))
        {
            _currentPointerID = pointerID;
            isPressed = true;
        }
        else if (_currentPointerID == pointerID && (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_POINTER_UP))
        {
            _currentPointerID = -1;
            isPressed = false;
        }

        // Handle the second pointer (Secondary touch)
        if (_secondPointerID == -1 && _currentPointerID != pointerID &&
                (action == MotionEvent.ACTION_POINTER_DOWN))
        {
            _secondPointerID = pointerID;
            isPressed2 = true;
        }
        else if (_secondPointerID == pointerID && (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_POINTER_UP))
        {
            _secondPointerID = -1;
            isPressed2 = false;
        }

        Log.d("TouchIDS", "Current Pointer " + _currentPointerID + "Second Pointer: " + _secondPointerID);




        if(_currentPointerID != -1)
        {
            boolean pointerToSecond = false;

            //If there is a finger touching the screen
            for(int i = 0; i < motionEvent.getPointerCount(); i++)
            {
                if(motionEvent.getPointerId(i) == _currentPointerID)
                {
                    if(motionEvent.getX() <= gameWidth / 2)
                    {
                        _position.x = motionEvent.getX(i);
                        _position.y = motionEvent.getY(i);
                    }
                    else
                    {
                        pointerToSecond = true;
                        SecondTouchPos.x = motionEvent.getX(i);
                        SecondTouchPos.y = motionEvent.getY(i);
                    }

                }
                else if(motionEvent.getPointerId(i) == _secondPointerID)
                {
                    if(pointerToSecond)
                    {
                        _position.x = motionEvent.getX(i);
                        _position.y = motionEvent.getY(i);
                    }
                    else
                    {
                        SecondTouchPos.x = motionEvent.getX(i);
                        SecondTouchPos.y = motionEvent.getY(i);
                    }
                }

            }
            Log.d("TouchPositions", "Current Pointer " + _position.x + " " + _position.y  + "Second Pointer: " + SecondTouchPos.x + " " + SecondTouchPos.y);


        }
    }
}
