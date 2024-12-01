package com.example.dx1221_elearning_wk3.mgp2d.core;

import android.graphics.Canvas;

public abstract class GameEntity {
    protected Vector2 _position = new Vector2(0, 0);
    public Vector2 getPosition() { return _position.copy(); }
    public void setPosition(Vector2 position) { _position = position; }

    protected Vector2 _size = new Vector2(0, 0);
    public Vector2 getSize() { return _size.copy(); }
    public void setSize(Vector2 size) { _size = size; }

    protected int _ordinal = 0;
    public int getOrdinal() { return _ordinal; }

    private boolean _isDone = false;
    public void destroy() { _isDone = true; }
    public boolean canDestroy() { return _isDone; }

    public void onUpdate(float dt) {}
    public abstract void onRender(Canvas canvas);
}
