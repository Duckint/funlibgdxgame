package com.ducky.game;

import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Levels
{
    public ArrayList<Rectangle> platformArray;
    public ArrayList<Rectangle> wallArray;
    public ArrayList<Enemies> enemy1Array;
    public void level1()
        {
            Platforms rectangle1 = Platforms.createRectangle(100, 80, 200, 20);
            Platforms rectangle2 = Platforms.createRectangle(400, 60, 200, 20);
            Platforms rectangle3 = Platforms.createRectangle(700, 100, 200, 20);
            Platforms wall1 = Platforms.createRectangle(80, 80, 20, 600);

            platformArray.add(rectangle1.getRectangle());
            platformArray.add(rectangle2.getRectangle());
            platformArray.add(rectangle3.getRectangle());
            wallArray.add(wall1.getRectangle());

            Enemies objen = new Enemies(600.0f, 120.0f, 32.0f, 32.0f, 30.0f, 500.0f, 600.0f);
            Enemies objen2 = new Enemies(1000.0f, 180.0f, 32.0f, 32.0f, 30.0f, 750.0f, 1000.0f);

            enemy1Array.add(objen);
            enemy1Array.add(objen2);
        }
}
