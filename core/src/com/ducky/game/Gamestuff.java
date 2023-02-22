package com.ducky.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Input.Keys;
import static com.badlogic.gdx.Gdx.graphics;
import com.badlogic.gdx.utils.TimeUtils;
import java.util.ArrayList;

public class Gamestuff extends ApplicationAdapter {

      float speedX = 60;
	  long startTime;

	  Player objMPlayer = new Player();

	  ShapeRenderer rects;
	  private ArrayList<Rectangle> rectangleArray;
	  private ArrayList<Rectangle> wallArray;
	  Platforms rectangle1 = Platforms.createRectangle(100, 80, 200, 20);





    public void platformCollision(Rectangle collidepos)
	{
		if(objMPlayer.player.overlaps(collidepos))
		{
			objMPlayer.playerVelocity.y = 0;
			objMPlayer.playerPos.y = collidepos.y + collidepos.height;
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
		 
		 objMPlayer.playerdraw();
		 input();


		 objMPlayer.playerPos.add(objMPlayer.playerVelocity);

		 platformCollision(rectangle1.getRectangle());

		 objMPlayer.playerVelocity.y -= objMPlayer.playerFallSpeed * graphics.getDeltaTime();
		 //gravity works now



		 rects.begin(ShapeRenderer.ShapeType.Line);
		 rects.rect(rectangle1.getRectangle().x, rectangle1.getRectangle().y, rectangle1.getRectangle().width, rectangle1.getRectangle().height) ;
         rects.end();

		 objMPlayer.player.setPosition(objMPlayer.playerPos.x, objMPlayer.playerPos.y);
	}
	
	@Override
	public void dispose () {
	}
}
