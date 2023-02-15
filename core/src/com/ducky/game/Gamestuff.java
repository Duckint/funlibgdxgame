package com.ducky.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Input.Keys;
import static com.badlogic.gdx.Gdx.graphics;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.TimeUtils;

public class Gamestuff extends ApplicationAdapter {

      float speedX = 60;
	  float speedY = 60;
	  long startTime;



	  //int test1 = 50;
	  //int test2 = 50;
	  //int test3 = 50;


	  Player objMPlayer = new Player();

	  ShapeRenderer rects;
	  Platforms rectangle1 = Platforms.createRectangle(100, 100, 200, 20);


    public void rectcollision()
	{
		if(Intersector.overlaps(objMPlayer.player, rectangle1.getRectangle()))
		{
			objMPlayer.canJump = true;
			startTime = TimeUtils.nanoTime();
		}
	}
	public void input()
	 {


		 if(Gdx.input.isKeyPressed(Keys.A))
		 {
			 objMPlayer.player.x -= speedX * graphics.getDeltaTime();
		 }
		 if(Gdx.input.isKeyPressed(Keys.D))
		 {
			 objMPlayer.player.x += speedX * graphics.getDeltaTime();
		 }
		 if(Gdx.input.isKeyPressed(Keys.W) && objMPlayer.canJump)
		 {
			 objMPlayer.player.y += speedY * graphics.getDeltaTime();
			 objMPlayer.isPlayerJumping = true;
		 }

	 }
    @Override
	public void create ()
	{

	   objMPlayer.playerRender = new ShapeRenderer();
	   rects = new ShapeRenderer();
	   startTime = TimeUtils.nanoTime();




	}
	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 1, 1);
		 float secondsElapsed = (TimeUtils.nanoTime() - startTime) / 1_000_000_000f;
		 objMPlayer.playerdraw();
		 input();

		 //there's probably a better way to do this, but I'll figure it out some other day
		 if(objMPlayer.isPlayerJumping)
		 {
            if(secondsElapsed >= 3 )			{
               objMPlayer.canJump = false;
			   objMPlayer.player.y -= objMPlayer.playerFallSpeed * graphics.getDeltaTime();

			}
		 }
		 rectcollision();


		 rects.begin(ShapeRenderer.ShapeType.Line);
		 rects.rect(rectangle1.getRectangle().x, rectangle1.getRectangle().y, rectangle1.getRectangle().width, rectangle1.getRectangle().height) ;
         rects.end();






	}
	
	@Override
	public void dispose () {
	}
}
