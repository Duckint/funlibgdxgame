package com.ducky.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Input.Keys;
import static com.badlogic.gdx.Gdx.graphics;
import com.badlogic.gdx.math.Intersector;

public class Gamestuff extends ApplicationAdapter {

      float speedX = 60;
	  float speedY = 60;

	  //int test1 = 50;
	  //int test2 = 50;
	  //int test3 = 50;


	  Player objMPlayer = new Player();
	  ShapeRenderer rects;
	  Platforms rectangle1 = Platforms.createRectangle(100, 100, 100, 100);

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
		 if(Gdx.input.isKeyPressed(Keys.W))
		 {
			 objMPlayer.player.y += speedY * graphics.getDeltaTime();
		 }
	 }
    @Override
	public void create ()
	{

	   objMPlayer.playerRender = new ShapeRenderer();
	   rects = new ShapeRenderer();


	}
	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 1, 1);

		 objMPlayer.playerdraw();
		 input();
		 if(Intersector.overlaps(objMPlayer.player, rectangle1.getRectangle()))
		 {
			 System.out.println("hey im colliding lmfao");
		 }
		 rects.begin(ShapeRenderer.ShapeType.Line);
		 rects.rect(rectangle1.getRectangle().x, rectangle1.getRectangle().y, rectangle1.getRectangle().width, rectangle1.getRectangle().height) ;
         rects.end();






	}
	
	@Override
	public void dispose () {
	}
}
