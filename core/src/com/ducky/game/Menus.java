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
     //public boolean getOption(){return optionclicked;}
     //public boolean getExit(){return exitclicked;}
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

        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.input.getY();
        for(Rectangle menu: menubuttons)
        {
            menurender.rect(menu.x, menu.y, menu.width, menu.height);
            if(Gdx.input.isKeyPressed(Input.Keys.I))
            {
                startclicked = true;
            }
        }
        menurender.end();
     }
}
