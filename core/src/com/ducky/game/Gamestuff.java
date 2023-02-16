package com.ducky.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Input.Keys;
import static com.badlogic.gdx.Gdx.graphics;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.TimeUtils;
import java.util.ArrayList;

public class Gamestuff extends ApplicationAdapter {

      float speedX = 60;
	  float speedY = 60;
	  long startTime;
	   public Vector2 playerPos;



	  //int test1 = 50;
	  //int test2 = 50;
	  //int test3 = 50;


	  Player objMPlayer = new Player();

	  ShapeRenderer rects;
	  private ArrayList<Rectangle> rectangleArray;
	  Platforms rectangle1 = Platforms.createRectangle(100, 100, 200, 20);





    public void rectcollision()
	{
		if(Intersector.overlaps(rectangleArray.get(0), rectangleArray.get(1)))
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
		objMPlayer.playerVelocity = new Vector2(0, 0);
		playerPos = new Vector2(50, 50);
		objMPlayer.player.x = playerPos.x;
		objMPlayer.player.y = playerPos.y;


		rectangleArray = new ArrayList<Rectangle>();

	   objMPlayer.playerRender = new ShapeRenderer();
	   rects = new ShapeRenderer();

	   startTime = TimeUtils.nanoTime();

	   rectangleArray.add(rectangle1.getRectangle());
	   rectangleArray.add(objMPlayer.player);
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
            if(secondsElapsed >= 2 )
			{
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
