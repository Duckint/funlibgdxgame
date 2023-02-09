package com.ducky.game;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Shapes  {

      ShapeRenderer shapes;


    public void rectangles(float recx, float recy, float recz, float recwidth, float recheight, float recdepth)
    {

        shapes.begin(ShapeRenderer.ShapeType.Line);
        shapes.box(recx, recy, recz, recwidth, recheight, recdepth);
        shapes.end();


      }

}
