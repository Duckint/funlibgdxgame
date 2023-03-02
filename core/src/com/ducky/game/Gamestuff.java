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

      float speedX = 90;
	  long startTime;
	  long elapsedTime;

	  Player objMPlayer = new Player();
	  Enemy objEnemy = new Enemy();

	  ShapeRenderer rects;
	  private ArrayList<Rectangle> platformArray;
	  private ArrayList<Rectangle> enemyArray;
	  private ArrayList<Rectangle> wallArray;
	  Platforms rectangle1 = Platforms.createRectangle(100, 80, 200, 20);
	  Platforms rectangle2 = Platforms.createRectangle(400, 60, 200, 20);









    public void platformCollision(Rectangle collidepos)
	{
		if(objMPlayer.player.overlaps(collidepos))
		{
			objMPlayer.playerVelocity.y = 0;
			objMPlayer.playerPos.y = collidepos.y + collidepos.height;
			objMPlayer.canJump = true;
			objMPlayer.canSlam = false;
			objMPlayer.isSlamming = false;
		}
	}
	public void input()
	 {
		 if(Gdx.input.isKeyPressed(Keys.R))
		 {
             objMPlayer.playerPos.x = 140;
			 objMPlayer.playerPos.y = 120;
		 }
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
			 objMPlayer.isPlayerJumping = true;
			 objMPlayer.canSlam = true;
		 }
		 if(Gdx.input.isKeyPressed(Keys.SPACE) && objMPlayer.canSlam && objMPlayer.isPlayerJumping)
		 {
			  objMPlayer.playerVelocity.y = -10.0f;
			  objMPlayer.isSlamming = true;
			  objMPlayer.canSlam = false;
		 }
	 }
	 public void Enemies()
	 {
		Rectangle enemy1rect1 = objEnemy.enemyType1(50, 50, 10, 10);
		enemyArray.add(enemy1rect1);
	 }
    @Override
	public void create ()
	{
		objMPlayer.playerVelocity = new Vector2(0, 0);


		platformArray = new ArrayList<Rectangle>();
		enemyArray = new ArrayList<Rectangle>();
		Enemies();

	   objMPlayer.playerRender = new ShapeRenderer();
	   rects = new ShapeRenderer();

	   startTime = TimeUtils.nanoTime();


	   platformArray.add(rectangle1.getRectangle());
	   platformArray.add(rectangle2.getRectangle());
	   platformArray.add(objMPlayer.player);


	}
	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 1, 1);

		 elapsedTime = System.nanoTime() - startTime;
		 objMPlayer.playerdraw();
		 input();


		 objMPlayer.playerPos.add(objMPlayer.playerVelocity);
		 objMPlayer.player.setPosition(objMPlayer.playerPos.x, objMPlayer.playerPos.y);

		 platformCollision(platformArray.get(0));
		 platformCollision(platformArray.get(1));

		 objMPlayer.playerVelocity.y -= objMPlayer.currentFallSpeed * graphics.getDeltaTime();



		 rects.begin(ShapeRenderer.ShapeType.Line);
		 rects.rect(platformArray.get(0).x, platformArray.get(0).y, platformArray.get(0).width, platformArray.get(0).height);
		 rects.rect(platformArray.get(1).x, platformArray.get(1).y, platformArray.get(1).width, platformArray.get(1).height);
         rects.rect(enemyArray.get(0).x, enemyArray.get(0).y, enemyArray.get(0).width, enemyArray.get(0).height);
         rects.end();


	}
	
	@Override
	public void dispose () {
	}
}
