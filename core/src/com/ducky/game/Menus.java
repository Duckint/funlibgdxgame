package com.ducky.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Menus {

     ShapeRenderer menurender;
     public ArrayList<Rectangle> menubuttons;
     private boolean optionclicked, exitclicked, startclicked;
     public Menus(boolean optionclicked, boolean exitclicked, boolean startclicked)
     {
         this.startclicked = startclicked;
         this.optionclicked = optionclicked;
         this.exitclicked = exitclicked;
     }
     public boolean getStart() {return startclicked;}
     public boolean getOption(){return optionclicked;}
     public boolean getExit(){return exitclicked;}
     public void mmC()//mainmenuCreate
     {
         Platforms start = Platforms.createRectangle(600, 600, 100, 100);
         Platforms options = Platforms.createRectangle(600, 450, 100, 100);
         Platforms exit = Platforms.createRectangle(600, 300, 100, 100);
         menubuttons.add(start.getRectangle());
         menubuttons.add(options.getRectangle());
         menubuttons.add(exit.getRectangle());
     }
     public void mainmenu()
     {
        menurender.begin(ShapeRenderer.ShapeType.Line);

        for(Rectangle button : menubuttons)
        {
            float mouseX = Gdx.input.getX();
            float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY() ;
            menurender.rect(button.x, button.y, button.width, button.height);
            if(menubuttons.get(0).contains(mouseX, mouseY) && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            {
                startclicked = true;
            }
            else if(menubuttons.get(1).contains(mouseX, mouseY) && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            {
                optionclicked = true;
            }
            else if(menubuttons.get(2).contains(mouseX, mouseY) && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            {
                exitclicked = true;
            }
            System.out.println("Mouse X: " + mouseX + " Mouse Y: " + mouseY);
        }
        menurender.end();
     }
}
