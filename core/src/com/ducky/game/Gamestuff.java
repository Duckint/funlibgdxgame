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
	  long startTime;




	  //int test1 = 50;
	  //int test2 = 50;
	  //int test3 = 50;


	  Player objMPlayer = new Player();

	  ShapeRenderer rects;
	  private ArrayList<Rectangle> rectangleArray;
	  Platforms rectangle1 = Platforms.createRectangle(100, 80, 200, 20);





    public void rectcollision()
	{
		if(Intersector.overlaps(rectangleArray.get(0), rectangleArray.get(1)))
		{
			startTime = TimeUtils.nanoTime();
			objMPlayer.playerVelocity.y = 0;
			objMPlayer.playerPos.y = 120;
			objMPlayer.canJump = true;
		}
	}
	public void input()
	 {
		 if(Gdx.input.isKeyPressed(Keys.A))
		 {
			 objMPlayer.playerPos.x -= speedX * graphics.getDeltaTime();
		 }
		 if(Gdx.input.isKeyPressed(Keys.D))
		 {
			 objMPlayer.playerPos.x += speedX * graphics.getDeltaTime();
		 }
		 if(Gdx.input.isKeyPressed(Keys.W) && objMPlayer.canJump)
		 {
			 objMPlayer.playerVelocity.y = objMPlayer.jumpPowa;
			 objMPlayer.canJump = false;
		 }
	 }
    @Override
	public void create ()
	{
		objMPlayer.playerVelocity = new Vector2(0, 0);



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

		 objMPlayer.playerPos.add(objMPlayer.playerVelocity);

		 objMPlayer.playerVelocity.y -= objMPlayer.playerFallSpeed * graphics.getDeltaTime();
		 //there's probably a better way to do this, but I'll figure it out some other day

		 if(objMPlayer.playerPos.y <= 102)
		 {
			 objMPlayer.playerVelocity.y = 0;
			 objMPlayer.playerPos.y = 102;
			 objMPlayer.canJump = true;
		 }

		 rects.begin(ShapeRenderer.ShapeType.Line);
		 rects.rect(rectangle1.getRectangle().x, rectangle1.getRectangle().y, rectangle1.getRectangle().width, rectangle1.getRectangle().height) ;
         rects.end();
	}
	
	@Override
	public void dispose () {
	}
}
