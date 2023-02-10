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

	  int test1 = 50;
	  int test2 = 50;
	  int test3 = 50;
	  Vector3 playerrecpos = new Vector3(50, 50, 50);
	  Shapes objstuff1 = new Shapes();



    @Override
	public void create ()
	{
       objstuff1.shapes = new ShapeRenderer();

	}
	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 1, 1);

		 objstuff1.rectangles(playerrecpos.x, playerrecpos.y, playerrecpos.z, 50, 50, 50);

		 objstuff1.rectangles(50, 50, 50, 80, 50, 50);

		 if(Gdx.input.isKeyPressed(Keys.D))
		 {
             playerrecpos.x += speedX * Gdx.graphics.getDeltaTime();
		 }
		if(Gdx.input.isKeyPressed(Keys.A))
		{
			playerrecpos.x -= speedX * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.W))
		{
			playerrecpos.y += speedY * Gdx.graphics.getDeltaTime();
		}


	}
	
	@Override
	public void dispose () {
	}
}
