package com.ducky.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class Enemies {

     private float eSpeedX;
     private float leftEdge;
     //these two edges determine the range of movement for the enemy before they turn around
     private float rightEdge;
     private Rectangle rectangle;
     public boolean enemyRender;
     //a reminder to myself that this should start true

     public Enemies(float startX, float startY, float width, float height, float eSpeedX, float leftEdge, float rightEdge)
     {

         rectangle = new Rectangle (startX, startY, width, height);
         this.eSpeedX = eSpeedX;
         this.leftEdge = leftEdge;
         this.rightEdge = rightEdge;
         this.enemyRender = true;
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
    public boolean getEnemyRender() {return enemyRender;}
    public void setEnemyRender(boolean render){this.enemyRender = render;}
    /*public Rectangle enemyType2()
    {
        return null;
    }*/
}
