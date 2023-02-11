package com.ducky.game;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Shapes {

    ShapeRenderer shapes;


    public void rectangles(float recx, float recy, float recwidth, float recheight)
    {


            shapes.begin(ShapeRenderer.ShapeType.Line);
            shapes.rect(recx, recy, recwidth, recheight);
            shapes.end();


    }


}
