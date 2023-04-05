package com.ducky.game;

import com.badlogic.gdx.math.Rectangle;


public class Platforms
{


    private Rectangle rectangle;


    private Platforms(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
    public Rectangle getRectangle() {
        return rectangle;
    }

    public static Platforms createRectangle(float x, float y, float width, float height) {
        //While this is in the Platforms class, it should easily work for any rectangle I need to draw.
        //Being used in the Enemies Class
        //Being used in the Gamestuff Class
        Rectangle rectangle = new Rectangle(x, y, width, height);
        return new Platforms(rectangle);
    }

}
