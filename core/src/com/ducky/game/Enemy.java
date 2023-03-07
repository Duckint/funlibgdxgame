package com.ducky.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class Enemy {


     private float eSpeedX = 90f;
     private float startX;
     private float startY;
     private float width;
     private float height;
     private float distanceTrvX;
     private Rectangle enrectangle;
     boolean movingRight = true;

     public Enemy(float startX, float startY, float width, float height, float eSpeedX, float distanceTrvX)
     {
         this.startX = startX;
         this.startY = startY;
         this.width = width;
         this.height = height;
         this.eSpeedX = eSpeedX;
         this.distanceTrvX = distanceTrvX;

         enrectangle = new Rectangle(startX, startY, width, height);
     }
     public float geteSpeedX()
     {
         return eSpeedX;
     }
    public void enemyType1()
    {
        float delta = Gdx.graphics.getDeltaTime();
        float x = startX;

        if(movingRight)
        {
           x += eSpeedX * delta;
           if(x >= startX + distanceTrvX)
           {
               movingRight = false;
               eSpeedX = -eSpeedX;
           }
        }
        else
        {
            x += eSpeedX * delta;
            if(x <= startX)
            {
                movingRight = true;
                eSpeedX = -eSpeedX;
            }
        }

    }
    public Rectangle getRectangle()
    {
        return new Rectangle(startX, startY, width, height);
    }
    public Rectangle enemyType2()
    {
        return null;
    }
}
