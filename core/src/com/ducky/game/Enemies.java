package com.ducky.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class Enemies {

     private float eSpeedX = 90.0f;
     private float startX;
     private float startY;
     private float width;
     private float height;
     private float leftEdge;
     private float rightEdge;
     private Rectangle rectangle;
     public boolean enemyRender = true;
     //a reminder to myself that this should start true

     public Enemies(float startX, float startY, float width, float height, float eSpeedX, float leftEdge, float rightEdge)
     {

         rectangle = new Rectangle (startX, startY, width, height);
         this.startX = startX;
         this.startY = startY;
         this.width = width;
         this.height = height;
         this.eSpeedX = eSpeedX;
         this.leftEdge = leftEdge;
         this.rightEdge = rightEdge;

     }

    public void enemyType1()
    {
        float delta = Gdx.graphics.getDeltaTime();

        if(rectangle.x < leftEdge)
        {
            rectangle.x = leftEdge;
            eSpeedX = -eSpeedX;
        }
        else if(rectangle.x + rectangle.width > rightEdge)
        {
           rectangle.x = rightEdge - rectangle.width;
           eSpeedX = -eSpeedX;
        }
        rectangle.x += eSpeedX * delta;
    }
    public Rectangle getRectangle()
    {
        return rectangle;
    }
    public Rectangle enemyType2()
    {
        return null;
    }
}
