package com.ducky.game;

import com.badlogic.gdx.math.Rectangle;

public class Enemy {

    public Rectangle enemyType1(float e1positionx, float e1positiony, float e1width, float e1height)
    {
        return Platforms.createRectangle(e1positionx, e1positiony, e1width, e1height).getRectangle();
    }
    public Rectangle enemyType2()
    {

        return null;
    }
}
