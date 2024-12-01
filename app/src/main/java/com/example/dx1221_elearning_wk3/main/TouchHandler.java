package com.example.dx1221_elearning_wk3.main;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.example.dx1221_elearning_wk3.mgp2d.core.GameActivity;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameEntity;
import com.example.dx1221_elearning_wk3.mgp2d.core.Vector2;

public class TouchHandler extends GameEntity {

    private int _currentPointerID;

    private boolean isPressed;
    public TouchHandler()
    {
        _currentPointerID = -1;
        _size = new Vector2(24, 24);
    }

    public boolean Pressed()
    {
        return isPressed;
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

        if(_currentPointerID == -1 &&
                (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_DOWN))
        {
            _currentPointerID = pointerID;
            isPressed = true;
        }
        else if (_currentPointerID == pointerID &&
                (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_POINTER_UP))
        {
            _currentPointerID = -1;
            isPressed = false;
        }


        if(_currentPointerID != -1)
        {
            //If there is a finger touching the screen
            for(int i = 0; i < motionEvent.getPointerCount(); i++)
            {
                if(motionEvent.getPointerId(i) != _currentPointerID) continue;
                _position.x = motionEvent.getX();
                _position.y = motionEvent.getY();
            }
        }
    }
}
