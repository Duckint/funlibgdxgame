package com.ducky.game;


import com.badlogic.gdx.Gdx;
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

	  Platforms objstuff1 = new Platforms();
	  Player objMPlayer = new Player();


    @Override
	public void create ()
	{
       objstuff1.platforms = new ShapeRenderer();
	   objMPlayer.playerRender = new ShapeRenderer();
	}
	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 1, 1);

		 objMPlayer.playerdraw();

		 objstuff1.rectangles(50, 50, 80, 50);



		 if(Gdx.input.isKeyPressed(Keys.D))
		 {
             objMPlayer.player.x += speedX * Gdx.graphics.getDeltaTime();
		 }
		if(Gdx.input.isKeyPressed(Keys.A))
		{
			objMPlayer.player.x  -= speedX * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.W))
		{
			objMPlayer.player.y += speedY * Gdx.graphics.getDeltaTime();
		}


	}
	
	@Override
	public void dispose () {
	}
}
