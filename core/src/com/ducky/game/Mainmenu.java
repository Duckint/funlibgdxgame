package com.ducky.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;

import static com.badlogic.gdx.Gdx.input;

public class Mainmenu {

     ShapeRenderer menurender;
     public ArrayList<Rectangle> menubuttons;
     private boolean startclicked;
     float mouseX;
     float mouseY;
     public Mainmenu(boolean startclicked)
     {
         this.startclicked = startclicked;
     }
     public boolean getStart() {return startclicked;}

     public void mmC()//mainmenuCreate
     {
         Platforms start = Platforms.createRectangle(600, 600, 100, 100);
         Platforms options = Platforms.createRectangle(600, 450, 100, 100);
         Platforms exit = Platforms.createRectangle(600, 300, 100, 100);
         menubuttons.add(start.getRectangle());
         menubuttons.add(options.getRectangle());
         menubuttons.add(exit.getRectangle());
         mouseX = input.getX();
         mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
     }
     public void mainmenu()
     {
        menurender.begin(ShapeRenderer.ShapeType.Line);

        for(Rectangle button : menubuttons)
        {
            menurender.rect(button.x, button.y, button.width, button.height);
            if(menubuttons.get(0).contains(mouseX, mouseY) && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            {
                startclicked = true;
            }
            else if(menubuttons.get(1).contains(mouseX, mouseY) && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            {
            }
            else if(menubuttons.get(2).contains(mouseX, mouseY) && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            {
                Gdx.app.exit();
            }
        }
        menurender.end();
     }
}
