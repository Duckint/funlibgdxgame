package com.ducky.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Input.Keys;

public class Gamestuff extends ApplicationAdapter {

      float speedX = 40;
	  float speedY = 60;

	  Vector3 playerrecsize = new Vector3(50, 50, 50);
	  Shapes objstuff1 = new Shapes();

    @Override
	public void create ()
	{
       objstuff1.shapes = new ShapeRenderer();
	}
	@Override
	public void render () {
		ScreenUtils.clear(0, 1, 1, 1);



		 objstuff1.rectangles(playerrecsize.x, playerrecsize.y, playerrecsize.z, 50, 50, 50);

		 if(Gdx.input.isKeyPressed(Keys.D))
		 {
             playerrecsize.x += speedX * Gdx.graphics.getDeltaTime();
		 }
		if(Gdx.input.isKeyPressed(Keys.A))
		{
			playerrecsize.x -= speedX * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.W))
		{
			playerrecsize.y += speedY * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.S))
		{
			playerrecsize.y -= speedY * Gdx.graphics.getDeltaTime();
		}
	}
	
	@Override
	public void dispose () {
	}
}
