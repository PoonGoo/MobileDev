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

    public boolean isColliding(GameEntity entityB) {
        // Calculate boundaries for entityA
        float aLeft = getPosition().x ;
        float aRight = getPosition().x + getSize().x;
        float aTop = getPosition().y;
        float aBtm = getPosition().y + getSize().y;

        // Calculate boundaries for entityB
        float bLeft = entityB.getPosition().x;
        float bRight = entityB.getPosition().x + entityB.getSize().x;
        float bTop = entityB.getPosition().y;
        float bBtm = entityB.getPosition().y + entityB.getSize().y;

        // Check for overlap between entityA and entityB
        return (aLeft < bRight && aRight > bLeft && aTop < bBtm && aBtm > bTop);
    }
}
