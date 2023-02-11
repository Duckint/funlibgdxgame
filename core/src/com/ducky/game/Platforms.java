package com.ducky.game;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Platforms {

    ShapeRenderer platforms;


    public void rectangles(float recx, float recy, float recwidth, float recheight )
    {


            platforms.begin(ShapeRenderer.ShapeType.Line);
            platforms.rect(recx, recy, recwidth, recheight);
            platforms.end();


    }


}
